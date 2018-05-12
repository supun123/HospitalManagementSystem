/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

/**
 *
 * @author A C E R
 */
public class patientBill {
    private int admition_id;
    private int treatment;
    private int Test;
    private int Other;
    private int total;

    /**
     * @return the treatment
     */
    public int getTreatment() {
        return treatment;
    }

    /**
     * @param treatment the treatment to set
     */
    public void setTreatment(int treatment) {
        this.treatment = treatment;
    }

    /**
     * @return the Test
     */
    public int getTest() {
        return Test;
    }

    /**
     * @param Test the Test to set
     */
    public void setTest(int Test) {
        this.Test = Test;
    }

    /**
     * @return the Other
     */
    public int getOther() {
        return Other;
    }

    /**
     * @param Other the Other to set
     */
    public void setOther(int Other) {
        this.Other = Other;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the admition_id
     */
    public int getAdmition_id() {
        return admition_id;
    }

    /**
     * @param admition_id the admition_id to set
     */
    public void setAdmition_id(int admition_id) {
        this.admition_id = admition_id;
    }
}
