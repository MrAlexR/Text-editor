import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import javax.swing.JOptionPane;

public class FileWritter 
{
	public FileWritter(String filename) throws Exceptions 
	{
		File newfile = new File(filename);
		if(newfile.exists())
		{
			JOptionPane.showMessageDialog(null, "File already exists!");
			throw new Exceptions("");
		}
		AddFile(filename);
	}
	
	public void AddTextString(String text)
	{
		byte[] array = text.getBytes();
		try {
			TargetFile.write(array);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to write to file!");
		}
	}
	
	public void CloseFile()
	{
		try {
			TargetFile.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to close file!");
		}
	}
	
	private void AddFile(String filename)
	{
		try {
			TargetFile = new FileOutputStream(filename);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to add file!");
		}
	}
	FileOutputStream TargetFile;
}
