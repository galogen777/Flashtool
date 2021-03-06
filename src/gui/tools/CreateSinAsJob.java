package gui.tools;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.system.OS;
import org.system.ProcessBuilderWrapper;

public class CreateSinAsJob extends Job {

	boolean canceled = false;
	String file;
	String partition;
	String spareinfo;
	private static Logger logger = Logger.getLogger(CreateSinAsJob.class);
	
	public CreateSinAsJob(String name) {
		super(name);
	}
	
	public void setFile(String f) {
		file=f;
	}
	
	public void setPartition(String part) {
		partition = part;
	}
	
	public void setSpare(String spare) {
		spareinfo = spare;
	}
    
	protected IStatus run(IProgressMonitor monitor) {
        if (file != null) {
			try {
				logger.info("Generating sin file to "+file+".sin");
				logger.info("Please wait");
				if (spareinfo.equals("09")) {
					ProcessBuilderWrapper command = new ProcessBuilderWrapper(new String[] {OS.getPathBin2Sin(),file, partition, "0x"+spareinfo,"0x20000"},false);
				}
				else {
					ProcessBuilderWrapper command = new ProcessBuilderWrapper(new String[] {OS.getPathBin2Sin(),file, partition, "0x"+spareinfo,"0x20000", "0x1000"},false);
				}
				logger.info("Sin file creation finished");
	    		return Status.OK_STATUS;
			}
			catch (Exception ex) {
				logger.error(ex.getMessage());
	    		return Status.CANCEL_STATUS;
			}
        }
        else {
        	logger.info("Create SIN As canceled (no selected data input");
    		return Status.CANCEL_STATUS;
        }
    }
}
