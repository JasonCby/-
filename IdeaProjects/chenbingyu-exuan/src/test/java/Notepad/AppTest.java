package Notepad;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException
     */
    public void testSave() throws IOException
    {
        Notepad testword = new Notepad();
        File file=new File("a.txt");
        testword.textArea.setText("123\n123\n");
        testword.savetxt(testword.textArea,file); //need to open the a.txt
        BufferedReader reader=null;
        String temp="";
        String a ;
        reader=new BufferedReader(new FileReader(file));
        while((a=reader.readLine())!=null){
            temp+=(a+"\n");
        }
        assertTrue( temp.equals(testword.textArea.getText()));
    }

    public void testOpen() throws IOException
    {
        Notepad testword = new Notepad();
        File file=new File("a.txt");
        testword.loadtxt(testword.textArea,file); //need to open the a.txt
        BufferedReader reader=null;
        String temp="";
        String a ;
        reader=new BufferedReader(new FileReader(file));
        while((a=reader.readLine())!=null){
        	temp+=(a+"\n");
        }
        assertTrue( temp.equals(testword.textArea.getText()));
    }


}
