package code.action;

import code.ViewerFrame;
import code.ViewerService;

public interface Action {
	void execute(ViewerService service,ViewerFrame frame);
}
