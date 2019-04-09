import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class Window extends JFrame
{
	public Window() 
	{
		//window stuff
		super("SimpleTextEditor");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		FileName = new String("");
		Text = new String("\n\n\n\t\t\tWelcome to a simple text editor!");
		Actions = new EventHandler();
	}
	
	public void GenerateWindow()
	{
		setSize(800, 720);
		CreateItems();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		TextSide.setText(Text);
	}
	
	private void CreateItems()
	{
		TextSide = new JTextArea(40, 60);
		add(TextSide);
		add(new JScrollPane(TextSide));
		TextSide.setEditable(false);
		
		NewFile = new JButton("Add");
		NewFile.setSize(80, 60);
		add(NewFile);
		NewFile.addActionListener(Actions);
		
		SaveFile = new JButton("Save");
		SaveFile.setSize(80, 60);
		add(SaveFile);
		SaveFile.addActionListener(Actions);
		SaveFile.setEnabled(false);
		
		QuitFile = new JButton("Exit file");
		QuitFile.setSize(80, 60);
		add(QuitFile);
		QuitFile.addActionListener(Actions);
		QuitFile.setEnabled(false);
	}
	
	String GetPanelText()
	{
		return TextSide.getText();
	}
	
	//variables for items
	FileWritter Exporter;
	EventHandler Actions;
	private JTextArea TextSide;
	private JButton NewFile;
	private JButton SaveFile;
	private JButton QuitFile;
	
	//variables for other stuff
	private String FileName;
	private String Text;
	
	//event class
	private class EventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent action)
		{
			//add file action button
			if(action.getSource() == NewFile)
			{
				JFileChooser opener = new JFileChooser();
				int result = opener.showSaveDialog(Window.this);
				if(result != JFileChooser.APPROVE_OPTION)
					return;
				
				FileName = new String(opener.getSelectedFile().toString());
				FileName = FileName.replace("\\", "\\\\");
				
				Text = new String("");
				TextSide.setText(Text);
				
				try {
					Exporter = new FileWritter(FileName);
				} catch (Exceptions e) {
					return;
				}
				
				SaveFile.setEnabled(true);
				NewFile.setEnabled(false);
				QuitFile.setEnabled(true);
				TextSide.setEditable(true);
				setTitle(FileName);
			}
			//save file action button
			if(action.getSource() == SaveFile)
			{
				Text = TextSide.getText();
				JOptionPane.showMessageDialog(null, "File saved!");
				Exporter.AddTextString(Text);
			}
			//quit file action button
			if(action.getSource() == QuitFile)
			{
				SaveFile.setEnabled(false);
				NewFile.setEnabled(true);
				QuitFile.setEnabled(false);
				Exporter.CloseFile();
				TextSide.setText("");
				TextSide.setEditable(false);
				setTitle("SimpleTextEditor");
			}
		}
	}
}
