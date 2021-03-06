package coursescheduleapp.view;

import java.sql.SQLException;

import coursescheduleapp.model.CourseCollectionDB;
import coursescheduleapp.model.CourseSelection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CoursePrefLB.java
 *
 * Created on Apr 6, 2010, 7:15:13 PM
 */

/**
 *
 * @author Drew
 */
public class CoursePrefLB extends javax.swing.JPanel {

	private CourseSelection courseSelect;
	private int whichCourse, whichEquiv;

	private CourseCollectionDB courseDB;
    /** Creates new form CoursePrefLB */
    public CoursePrefLB() {
        initComponents();
        
    }
    
    public void setCoursePref(CourseSelection courseSelect, int whichCourse, int whichEquiv, CourseCollectionDB courseDB)
    {
    	this.courseSelect = courseSelect;
    	this.whichCourse = whichCourse;
    	this.whichEquiv = whichEquiv;
    	this.courseDB = courseDB;
    	
    	setLabel();
    	try {
			courseChoiceLB1.setCoursePref(courseSelect, whichCourse, whichEquiv, courseDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    }
    public void setLabel()
    {
    	if (whichCourse == 0)
    	{
    		courseLB.setText("Course " + (whichEquiv + 1) + "   ");
    	}
    	else
    	{
    		courseLB.setText("             ");
    	}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        courseLB = new javax.swing.JLabel();
        courseChoiceLB1 = new CourseChoiceLB();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        courseLB.setText("Course");
        add(courseLB);
        add(courseChoiceLB1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CourseChoiceLB courseChoiceLB1;
    private javax.swing.JLabel courseLB;
    // End of variables declaration//GEN-END:variables

}
