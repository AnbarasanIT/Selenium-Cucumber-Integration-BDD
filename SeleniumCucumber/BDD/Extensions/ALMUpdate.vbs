'Purpose: To update TCs executed via Selenium on to HP ALM
'Author - Ramgopal Narayanan.S


'1. Declare and initialize required variables and objects.
'*******************************************************************
Dim gobjFso, gobjMyFile, gblnDebugMode, gobjQtpApp, garrQtpAddins, gblnActivateOK, gstrError
Dim gstrServerURL, gstrDomainName, gstrProjectName, gstrUserName, gstrPassword
Dim gstrTestSet,gstrQCRunControllerFile
Dim objExcel, objWorkBook, objWorkSheet, gstrCurrentDirectory
Dim intRowCount, intRowIterator
Set oShell = CreateObject("WScript.Shell")
Set gobjFso = CreateObject("Scripting.FileSystemObject")

'1.a. Get the arguments passed to the vbs thru' Java
'****************************************************
Set objArgs = WScript.Arguments
AllScenarioDetails=objArgs.item(0) 'All Scenario Details
arrAllScenarioDetails=SPLIT(AllScenarioDetails,"~")
CurrentRunScreenshotPath=objArgs.item(1) 'Screenshot path for the current run

'1.b Get the current directory of vbs
'*********************************
oShell.CurrentDirectory = gobjFso.GetParentFolderName(Wscript.ScriptFullName)
gstrCurrentDirectory=CStr(oShell.CurrentDirectory)

'1.c. Read the INI File
'*****************
Call ReadQCDataINI(gstrCurrentDirectory)


'2.	Connect to QC, if the QC integration is in place.
'*********************************************************
'Can be changed

Set tdc = CreateObject("tdapiole80.tdconnection")
if NOT(tdc.Connected) then

	tdc.InitConnectionEx gstrServerURL
	tdc.Login gstrUserName, gstrPassword
	tdc.Connect gstrDomainName, gstrProjectName
End If


'3. Get info on the current test set 
'************************************
Set TestSetFact = tdc.TestSetFactory
Set tsTreeMgr = tdc.TestSetTreeManager
Set tSetFolder = tsTreeMgr.NodeByPath(gstrTestSetFolder)
Set TestSetsList = tSetFolder.FindTestSets(gstrTestSetName)
Set theTestSet = TestSetsList.Item(1)
Set TSTestFact = theTestSet.TSTestFactory
Set TSTestFilter=TSTestFact.Filter

'Test Cases in test set
Set TestSetTestsList = TSTestFact.NewList("")
intCounter=0

'4. Read the test set row by row and change the status of the corresponding TC
'******************************************************************************
	WScript.Echo "ALM UPDATE STARTED - " & NOW()
	WScript.Echo vbcrlf
	
	For intScnCnt=0 To UBOUND(arrAllScenarioDetails)
			
		For Each theTSTest In TestSetTestsList 'RUN THRU' EACH TEST CASE IN THE TEST SET
		
			strTCName=theTSTest.Field("TS_NAME") 'TC ID in ALM Test Set
			isTCMatch="NO"
			arrScenarioDetails=SPLIT(arrAllScenarioDetails(intScnCnt),",")
			strScenrioID=arrScenarioDetails(0)		'SCENARIO ID
			strFeatureFile=arrScenarioDetails(1)	'FEATURE FILE NAME
			strScenrioStatus=arrScenarioDetails(2)  'SCENARIO STAUS
			strScenrioRunDuration=arrScenarioDetails(3)  'SCENARIO RUN DURATION
			strScenrioFileName=arrScenarioDetails(4)'SCENARIO XLS FILE TO BE UPLOADED 
			
			'Match the scenario id/test case id with the test case in ALM
			If  UCASE(TRIM(strScenrioID))=UCASE(TRIM(strTCName)) Then
				
				WScript.Echo UCASE(TRIM(strTCName))
				
				'4.a. Update the Date and Time 
				arrExecDateTime=SPLIT(NOW()," ")
				theTSTest.Field("TC_EXEC_DATE") = TRIM(arrExecDateTime(0))
				theTSTest.Post
				theTSTest.Field("TC_EXEC_TIME") = TRIM(arrExecDateTime(1))
				theTSTest.Post
				
				'4.b. Update the Date, Time, Duration and attach the file for the New Run
				
				'NEW RUN
				Set objRunFactory = theTSTest.RunFactory
				Set objThisRun = objRunFactory.AddItem("AUTOMATED_RUN" & "_" & TRIM(arrExecDateTime(0)) & "_" & TRIM(arrExecDateTime(1)))
				
				'STATUS
				If UCASE(TRIM(strScenrioStatus))="PASS" Then
					objThisRun.Status="Passed"
					objThisRun.Post
				ElseIf UCASE(TRIM(strScenrioStatus))="FAIL" Then
					objThisRun.Status="Failed"
					objThisRun.Post
				End If
				
				'DURATION
				objThisRun.Field("RN_DURATION")=strScenrioRunDuration
				objThisRun.Post
				
				'DATE & TIME
				objThisRun.Field("RN_EXECUTION_DATE") = TRIM(arrExecDateTime(0))
				objThisRun.Post
				objThisRun.Field("RN_EXECUTION_TIME") = TRIM(arrExecDateTime(1))
				objThisRun.Post
				
				'ATTACHMENT 
				'a. XLS REPORT
				Set objAttachments=objThisRun.Attachments
				Set objAttachment=objAttachments.AddItem(Null)
				objAttachment.FileName =strScenrioFileName
				objAttachment.Type=1
				objAttachment.Post	
				objAttachment.Refresh
				
				'b. SCREENSHOT
				
				'LOOP THRU THE SCREENSHOT FOLDER AND ATTACH ALL FILES THAT HAS THE SCENARIO ID + FF AS PART OF IT'S NAME
				
				Set fScreenShotFolder = gobjFso.GetFolder(CurrentRunScreenshotPath) 
				Set fScreenShotFiles = fScreenShotFolder.Files 

				For Each screenShotFile in fScreenShotFiles 

					If INSTR(1,screenShotFile.Name,strScenrioID) And INSTR(1,screenShotFile.Name,strFeatureFile) Then
						Set objAttachments=objThisRun.Attachments
						Set objAttachment=objAttachments.AddItem(Null)
						objAttachment.FileName =CurrentRunScreenshotPath & "\" & screenShotFile.Name
						objAttachment.Type=1
						objAttachment.Post	
						objAttachment.Refresh
					End If
					
				next 
				
				isTCMatch="YES"				
				Exit For 'Exit from the TEST SET LOOP			
			End If			
		Next
	Next
	
	
	'Log Unmatching Test Cases for Reference
	IF isTCMatch="NO" THEN
		LogUnMatchingCases
	END IF
	
If tdc.Connected Then
	tdc.Logout 
	tdc.Disconnect 
End If	
	
			
'Release all objects
'*******************
	
Set objWorkSheet = Nothing
Set objWorkBook = Nothing
Set objExcel = Nothing
Set gobjFso=Nothing
Set TestSetFact = Nothing
Set tsTreeMgr = Nothing
Set tSetFolder = Nothing
Set TestSetsList = Nothing
Set theTestSet = Nothing
Set TSTestFact = Nothing
Set TSTestFilter=Nothing
Set objRunFactory=Nothing
Set objThisRun=Nothing
tdc.Logout 
tdc.Disconnect 
Set tdc=Nothing

WScript.Echo "ALM UPDATE ENDED - " & NOW()
'********************************************************************************************************************************************************************
'#####################################################################################################################
'Function Name    		: ReadQCDataINI
'Description     		: Function to read the QC data ini file. User can use the same variable specified in the file
'Input Parameters 		: Current Directory
'Return Value    		: Nothing
'Author				    : S. Ramgopal Narayanan
'Date Created			: 14/3/11
'#####################################################################################################################
Function ReadQCDataINI(CurrentDirectory)
	Dim arrFileLines(),strQCDataPath
	intCntr = 0
	Set objFSO = CreateObject("Scripting.FileSystemObject")
	strQCDataPath = CurrentDirectory & "\ALMDATA.ini"
	Set objFile = objFSO.OpenTextFile(strQCDataPath, 1)

	Do Until objFile.AtEndOfStream
		 Redim Preserve arrFileLines(intCntr)
		 arrFileLines(intCntr) = objFile.ReadLine
		 intCntr = intCntr + 1
	Loop
	objFile.Close
	For intCount = Ubound(arrFileLines) to 1  Step -1
		If arrFileLines(intCount)<>"" Then 
			strTemp=split (arrFileLines(intCount),"=")
			strVar= strTemp(0)
			strVal=strTemp(1)  
			Execute strVar & " = strVal"
		End If
	Next
	Set objFSO=Nothing
End Function
'#####################################################################################################################

