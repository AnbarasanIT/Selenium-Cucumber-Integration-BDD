'Purpose: To update TCs executed via selenium
'Authot - Ram (229673)


'1. Declare and initialize required variables and objects.
'*******************************************************************
Dim gobjFso, gobjMyFile, gblnDebugMode, gobjQtpApp, garrQtpAddins, gblnActivateOK, gstrError
Dim gstrServerURL, gstrDomainName, gstrProjectName, gstrUserName, gstrPassword
Dim gstrTestSet,gstrQCRunControllerFile
Dim objExcel, objWorkBook, objWorkSheet, gstrCurrentDirectory
Dim intRowCount, intRowIterator
Set oShell = CreateObject("WScript.Shell")
Set gobjFso = CreateObject("Scripting.FileSystemObject")



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

If tdc.Connected Then

	tdc.Logout 
	tdc.Disconnect 
End If

if NOT(tdc.Connected) then

	tdc.InitConnectionEx gstrServerURL
	tdc.Login gstrUserName, gstrPassword
	tdc.Connect gstrDomainName, gstrProjectName
	msgbox "QC CONNECTION DONE"
	
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
msgbox "CONNECTED TO " & gstrTestSetName


		
	
			
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

'#####################################################################################################################
'Function Name    		: LogUnMatchingCases(strTCName)
'Description     		: Function to log the unmatching TC name onto a text file
'Input Parameters 		: TC Name
'Return Value    		: Nothing
'Author				    : S. Ramgopal Narayanan
'Date Created			: 14/3/11
'#####################################################################################################################
Function LogUnMatchingCases(strTCName)
	Set objFSO = CreateObject("Scripting.FileSystemObject")	
	Set objLogFile = objFSO.OpenTextFile(CurrentDirectory & "\" & gstrLogFile,8,True)
	objLogFile.WriteLine(strTCName)
	objLogFile.Close
	Set objFSO=Nothing
End Function
'#####################################################################################################################
