package code.action;

import code.ViewerFrame;
import code.ViewerService;

public class LastAction implements Action{

	@Override
	public void execute(ViewerService service, ViewerFrame frame) {
		// TODO Auto-generated method stub
		service.last(frame);
	}

}
