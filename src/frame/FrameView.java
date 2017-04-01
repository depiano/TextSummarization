package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import bean.Msg;
import search.LuceneTester;
import store.Store;

public class FrameView extends JFrame{
	private String nameProject, keyword;
	private int yearFrom, yearTo, monthFrom, monthTo;
	private JFrame win,f;

	private Container c;
	private GregorianCalendar calendar;
	private int annoCorrente, meseCorrente;
	private Font font, fontTitle;
	private Color color;
	private JPanel titlePnl,inputPnl,inputProjectPnl,inputDatePnl,buttonPnl,pathPnl,summarizationPathPnl,dataMiningPathPanl;
	private Icon textSummarizationIcon,dataMiningIcon;
	private JLabel title,keywordField, YearFromField,YearToField,MonthFromField,MonthToField,NameProjectField,DataMiningField,SummarizeField,DataMiningOutputText, SummarizeOutputText;
	private JTextField NameProjectText,keyText;
	private JButton DataMiningButton,SummarizeButton;
	private SpinnerModel yearModel, yearModel1,monthModel,monthModel1;
	private JSpinner YearFrom, YearTo, MonthFrom, MonthTo;


	private static final int PANEL_WIDTH = 650;
	private static final int PANEL_HEIGHT = 480;

	public FrameView (){
		nameProject="";
		keyword="";
		yearFrom=0;
		yearTo=0;
		monthFrom=0;
		monthTo=0;
		calendar = new GregorianCalendar();
		annoCorrente = calendar.get(Calendar.YEAR);
		meseCorrente = calendar.get(Calendar.MONTH);	
		fontTitle=new Font("Palatino Linotype", Font.BOLD, 34);
		font=new Font("Palatino Linotype",Font.BOLD,14);
		color=new Color(255,165,0);
		titlePnl=new JPanel();
		inputPnl=new JPanel();
		inputProjectPnl = new JPanel();
		inputDatePnl = new JPanel();
		buttonPnl=new JPanel();
		pathPnl=new JPanel();
		summarizationPathPnl=new JPanel();
		dataMiningPathPanl=new JPanel();

		textSummarizationIcon=new ImageIcon("/home/depiano/Scrivania/Project/TextProcessing/src/frame/2.png");
		dataMiningIcon=new ImageIcon("/home/depiano/Scrivania/Project/TextProcessing/src/frame/1.png");

		title=new JLabel("Mining Mailing List");
		title.setForeground(Color.WHITE);
		title.setFont(fontTitle);
		keywordField=new JLabel("Keyword:");
		keywordField.setFont(font);
		YearFromField=new JLabel("Year from:");
		YearFromField.setFont(font);
		YearToField=new JLabel("Year to:");
		YearToField.setFont(font);
		MonthFromField=new JLabel("Month from:");
		MonthFromField.setFont(font);
		MonthToField=new JLabel("Month to:");
		MonthToField.setFont(font);
		NameProjectField=new JLabel("Name Project:");
		NameProjectField.setFont(font);
		DataMiningField=new JLabel("Data Mining:");
		DataMiningField.setFont(font);
		DataMiningField.setVisible(false);
		SummarizeField=new JLabel("Summarize:");
		SummarizeField.setFont(font);
		SummarizeField.setVisible(false);

		NameProjectText=new JTextField(20);
		keyText=new JTextField(20);
		DataMiningOutputText=new JLabel("");
		DataMiningOutputText.setFont(font);
		SummarizeOutputText=new JLabel("");
		SummarizeOutputText.setFont(font);

		DataMiningButton=makeButtonDataMining(dataMiningIcon);
		SummarizeButton=makeButtonSummarization(textSummarizationIcon);
		yearModel = new SpinnerNumberModel(annoCorrente, annoCorrente - 10, annoCorrente + 10, 1);
		yearModel1 = new SpinnerNumberModel(annoCorrente, annoCorrente - 10, annoCorrente + 10, 1);
		monthModel = new SpinnerNumberModel(meseCorrente, 1, 12, 1);
		monthModel1 = new SpinnerNumberModel(meseCorrente, 1, 12, 1);


		titlePnl.setBackground(color);
		inputPnl.setBackground(color);
		inputProjectPnl.setBackground(color);
		inputDatePnl.setBackground(color);
		pathPnl.setBackground(color);
		buttonPnl.setBackground(color);
		summarizationPathPnl.setBackground(color);
		dataMiningPathPanl.setBackground(color);


		YearFrom=new JSpinner();
		YearFrom.setModel(yearModel);
		YearFrom.setEditor(new JSpinner.NumberEditor(YearFrom, "#"));	
		YearTo=new JSpinner();
		YearTo.setModel(yearModel1);
		YearTo.setEditor(new JSpinner.NumberEditor(YearTo, "#"));	
		MonthFrom=new JSpinner();
		MonthFrom.setModel(monthModel);
		MonthFrom.setEditor(new JSpinner.NumberEditor(MonthFrom, "#"));
		MonthTo=new JSpinner();
		MonthTo.setModel(monthModel1);
		MonthTo.setEditor(new JSpinner.NumberEditor(MonthTo, "#"));


		titlePnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		titlePnl.add(title);

		inputProjectPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		inputProjectPnl.add(NameProjectField);
		inputProjectPnl.add(NameProjectText);
		inputProjectPnl.add(keywordField);
		inputProjectPnl.add(keyText);

		inputDatePnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		inputDatePnl.add(YearFromField);
		inputDatePnl.add(YearFrom);
		inputDatePnl.add(MonthFromField);
		inputDatePnl.add(MonthFrom);
		inputDatePnl.add(YearToField);
		inputDatePnl.add(YearTo);

		inputDatePnl.add(MonthToField);
		inputDatePnl.add(MonthTo);

		inputPnl.setLayout(new GridLayout(3, 1));
		inputPnl.add(titlePnl);
		inputPnl.add(inputProjectPnl);
		inputPnl.add(inputDatePnl);

		dataMiningPathPanl.setLayout(new FlowLayout(FlowLayout.LEFT));
		dataMiningPathPanl.add(DataMiningField);
		dataMiningPathPanl.add(DataMiningOutputText);

		summarizationPathPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
		summarizationPathPnl.add(SummarizeField);
		summarizationPathPnl.add(SummarizeOutputText);

		pathPnl.setLayout(new GridLayout(2,1));
		pathPnl.add(dataMiningPathPanl);
		pathPnl.add(summarizationPathPnl);

		buttonPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		DataMiningButton.setBackground(Color.WHITE);
		SummarizeButton.setBackground(Color.WHITE);
		buttonPnl.add(DataMiningButton);
		buttonPnl.add(SummarizeButton);


		c = getContentPane();
		c.setBackground(new Color(171,205,239));
		c.setLayout(new BorderLayout());
		c.add(inputPnl, BorderLayout.NORTH);
		c.add(pathPnl, BorderLayout.CENTER);
		c.add(buttonPnl, BorderLayout.SOUTH);


		setLocation(500,200);
		setSize(PANEL_WIDTH,PANEL_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	class dataMiningListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("CIAO");
		}

	}

	class summarizationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Stronzo");				
		}

	}
	/**
	 Crea un bottone per cambiare il colore del pannello
	 @param label l�etichetta del bottone
	 @param color il colore da settare
	 @return button il bottone per cambiare il colore del pannello
	 */

	public JButton makeButtonDataMining(Icon icon){
		JButton button = new JButton(icon);
		class ButtonListener implements ActionListener{
			public void actionPerformed (ActionEvent event){
				SimpleRunner r = new SimpleRunner();
				Thread t = new Thread(r);
				t.start();
				
				f=new JFrame("Loading");
				ImageIcon i = new ImageIcon("/home/depiano/Scrivania/Project/TextProcessing/src/main/loading.gif");
				JLabel gifLabel=new JLabel(i);
				JPanel loadingPnl=new JPanel();
				JPanel center=new JPanel();
				loadingPnl.setLayout(new BorderLayout());
				loadingPnl.add(gifLabel, BorderLayout.CENTER);
				loadingPnl.setBackground(Color.WHITE);
				
				JPanel textPnl=new JPanel();
				textPnl.setLayout(new BorderLayout());
				JLabel loadingText=new JLabel("LOADING...");
				loadingText.setBackground(Color.WHITE);
				loadingText.setFont(font);
				textPnl.add(loadingText,BorderLayout.CENTER);
				textPnl.setBackground(Color.WHITE);
			
				center.setLayout(new BorderLayout());
				center.add(loadingPnl,BorderLayout.CENTER);
				center.add(textPnl, BorderLayout.WEST);

				f.add(center);
				f.setUndecorated(true);
				f.setSize(350,200);
				f.setBackground(Color.WHITE);
				f.setLocation(700,400);
				f.setVisible(true);
				f.setResizable(false);

				/*			nameProject=NameProjectText.getText();
				keyword=keyText.getText();
				yearFrom=(Integer)YearFrom.getValue();
				yearTo=(Integer)YearTo.getValue();
				monthFrom=(Integer)MonthFrom.getValue();
				monthTo=(Integer)MonthTo.getValue();
				try
				{
					Store st=new Store(nameProject,yearFrom,monthFrom,yearTo,monthTo);
					//Scarico i messaggi dall'archivio
					if(st.download()){

						ArrayList<String> files=LuceneTester.test(keyword);
						ArrayList<Msg> list_msg=st.analyzer(files);
						for(int i=0;i<list_msg.size();i++)
						{
							System.out.println(list_msg.get(i).toString());
						}
						st.saveMsgs(list_msg);
						DataMiningField.setVisible(true);
						DataMiningOutputText.setText(st.getDirOutput().toString());
						JOptionPane.showMessageDialog(win, "Operazione eseguita correttamente.");
						if(Desktop.isDesktopSupported()){
							File toOpen = new File("C:/Users/fdeci/Desktop/store_messages/output/output.dat");
							try {
								Desktop.getDesktop().open(toOpen);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
					else
						System.out.println("\nError: invalid project name!\n");

				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(win, "Attenzione:\nInserire correttamente tutti i parametri",   "Errore", JOptionPane.ERROR_MESSAGE);
				}
				 */	
			}		
		}
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		return button;
	}

	public JButton makeButtonSummarization(Icon icon){
		JButton button = new JButton(icon);
		class ButtonListener implements ActionListener{
			public void actionPerformed (ActionEvent event){


			}
		}			

		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		return button;
	}

	public class SimpleRunner implements Runnable{
		public void run(){
			nameProject=NameProjectText.getText();
			keyword=keyText.getText();
			yearFrom=(Integer)YearFrom.getValue();
			yearTo=(Integer)YearTo.getValue();
			monthFrom=(Integer)MonthFrom.getValue();
			monthTo=(Integer)MonthTo.getValue();
			try
			{
				Store st=new Store(nameProject,yearFrom,monthFrom,yearTo,monthTo);
				//Scarico i messaggi dall'archivio
				if(st.download()){

					ArrayList<String> files=LuceneTester.test(keyword);
					ArrayList<Msg> list_msg=st.analyzer(files);
					for(int i=0;i<list_msg.size();i++)
					{
						System.out.println(list_msg.get(i).toString());
					}
					st.saveMsgs(list_msg);
					DataMiningField.setVisible(true);
					DataMiningOutputText.setText(st.getDirOutput().toString());
					f.dispose();
					JOptionPane.showMessageDialog(win, "Operazione eseguita correttamente.");
					if(Desktop.isDesktopSupported()){
						File toOpen = new File("/home/depiano/Scrivania/store_messages/output/output.dat");
						try {
							Desktop.getDesktop().open(toOpen);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				else
					System.out.println("\nError: invalid project name!\n");
					f.dispose();
			}
			catch(Exception e)
			{
				f.dispose();
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(win, "Attenzione:\nInserire correttamente tutti i parametri",   "Errore", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}


