package main;

import java.util.ArrayList;



import bean.Msg;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import search.LuceneTester;
import store.Store;

/**
 * 
 * @author Antonio De Piano
 * Email: depianoantonio@gmail.com
 * web site: depiano.it
 *
 */

public class Test
{

    public static void main(String[] args)
    {
        try
        {
            Store st=new Store("lucene-general",2010,10,2010,12);
            //Scarico i messaggi dall'archivio
            if(st.download())
            {
                ArrayList<String> files=LuceneTester.test("Fork");
                ArrayList<Msg> list_msg=st.analyzer(files);
                for(int i=0;i<list_msg.size();i++)
                {
                    System.out.println(list_msg.get(i).toString());
                }
                st.saveMsgs(list_msg);
                st.formatedOutput(list_msg);
            }
            else
                System.out.println("\nError: invalid project name!\n");
            
            File dir_text_teaser = new File("/home/depiano/Scrivania/Project/TextProcessing/TextTeaser");
            if (dir_text_teaser.exists())
            {
                File[] files = dir_text_teaser.listFiles();
                for(int i=0;i<files.length;i++)
                    if(files[i].isFile())
                        files[i].delete();
            }
            else
                dir_text_teaser.mkdir();

            	
            String dir="/home/depiano/Scrivania/Project/TextProcessing/TextTeaser";
            System.out.println("Start cloning into " + dir);
            String[] cmd=null;
            String url="https://github.com/DataTeaser/textteaser.git";
            String projectName = "textteaser";
            
            File file = dir_text_teaser;
            if (file.exists()) {
               cmd = new String[]{"git", "clone", url, projectName};
                }
                Process p = Runtime.getRuntime().exec(cmd, null, file);
                p.waitFor();
		    System.out.println("End cloning");
		    
		    
		    String cmd1=new String("pip install -r /home/depiano/Scrivania/Project/TextProcessing/TextTeaser/textteaser/requirements.txt");
		    p = Runtime.getRuntime().exec(cmd1);
		
			InputStream inputS = p.getInputStream();
			InputStreamReader inputSRead = new InputStreamReader(inputS);
			BufferedReader buffRead = new BufferedReader(inputSRead);
			
			p.waitFor();
			String line;
			
			while ((line = buffRead.readLine()) != null) {
			    System.out.println(line);
			}
			
			System.out.println("copio script python");
			cmd1=new String("cp /home/depiano/Scrivania/Project/TextProcessing/script/summarize.py /home/depiano/Scrivania/Project/TextProcessing/TextTeaser/textteaser/");
			p = Runtime.getRuntime().exec(cmd1);
			
			
			System.out.println("Eseguo script python");
			cmd1=new String("python /home/depiano/Scrivania/Project/TextProcessing/TextTeaser/textteaser/summarize.py");
			p = Runtime.getRuntime().exec(cmd1);
			
			inputS = p.getInputStream();
			inputSRead = new InputStreamReader(inputS);
			buffRead = new BufferedReader(inputSRead);
		
			
			while ((line = buffRead.readLine()) != null)
			{
				System.out.println(line);
			}
			
		}
		catch(Exception e)
		{
		       System.out.println(e.getMessage());
		}
		        
	}
    		
}
