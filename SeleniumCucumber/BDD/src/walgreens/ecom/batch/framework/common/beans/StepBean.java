package walgreens.ecom.batch.framework.common.beans;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.Map;

public class StepBean {

    private boolean isFinalStep = false;
    private boolean isFirstStep = false;
    private int stepId = 0;
    private String stepScenarioId = null;
    private int stepImageId = 0;
    private String stepName = null;
    private String stepStatus = null;
    private String statusDescription = null;
    private Map<String, String> stepStatusMessages = null;
    private String stepCurrentUrl = null;
    private byte[] stepImage = null;
    private Date stepCreateDttm = null;
    private Date stepUpdateDttm = null;
    private BufferedImage screenShot = null;

    public BufferedImage getScreenShot() {
	return screenShot;
    }

    public void setScreenShot(BufferedImage screenShot) {
	this.screenShot = screenShot;
    }

    public int getStepId() {
	return stepId;
    }

    public void setStepId(int stepId) {
	this.stepId = stepId;
    }

    public String getStepScenarioId() {
	return stepScenarioId;
    }

    public void setStepScenarioId(String stepScenarioId) {
	this.stepScenarioId = stepScenarioId;
    }

    public int getStepImageId() {
	return stepImageId;
    }

    public void setStepImageId(int stepImageId) {
	this.stepImageId = stepImageId;
    }

    public String getStepStatus() {
	return stepStatus;
    }

    public void setStepStatus(String stepStatus) {
	this.stepStatus = stepStatus;
    }

    public byte[] getStepImage() {
	return stepImage;
    }

    public void setStepImage(byte[] stepImage) {
	this.stepImage = stepImage;
    }

    public Date getStepCreateDttm() {
	return stepCreateDttm;
    }

    public void setStepCreateDttm(Date stepCreateDttm) {
	this.stepCreateDttm = stepCreateDttm;
    }

    public Date getStepUpdateDttm() {
	return stepUpdateDttm;
    }

    public void setStepUpdateDttm(Date stepUpdateDttm) {
	this.stepUpdateDttm = stepUpdateDttm;
    }

    public String getStepName() {
	return stepName;
    }

    public void setStepName(String stepName) {
	this.stepName = stepName;
    }

    public String getStepCurrentUrl() {
	return stepCurrentUrl;
    }

    public void setStepCurrentUrl(String stepCurrentUrl) {
	this.stepCurrentUrl = stepCurrentUrl;
    }

    public boolean isFinalStep() {
	return isFinalStep;
    }

    public void setFinalStep(boolean isFinalStep) {
	this.isFinalStep = isFinalStep;
    }

    public String getStatusDescription() {
	return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
	this.statusDescription = statusDescription;
    }

    public Map<String, String> getStepStatusMessages() {
	return stepStatusMessages;
    }

    public void setStepStatusMessages(Map<String, String> stepStatusMessages) {
	this.stepStatusMessages = stepStatusMessages;
    }

    // RAM (1/14/13) - Added for reporting purpose
    // ---------------------------------------------------------------------------------------

    public boolean isFirstStep() {
	return isFirstStep;
    }

    public void setFirstStep(boolean isFirstStep) {
	this.isFirstStep = isFirstStep;
    }

    // ---------------------------------------------------------------------------------------

}
