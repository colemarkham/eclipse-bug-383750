/**
 * 
 */
package bug383750;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

/**
 * @author cole
 *
 */
public class SampleChildViewPart extends ViewPart implements ISelectionListener {

	private Text text1;
	private Text text2;
	private Composite parent;

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		new List(parent, SWT.NONE);
		text1 = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text2 = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text1.setText("text1");
		text2.setText("text 2 -- if you click me at first, I will be replaced by <text 1>!!");
		
		GridLayoutFactory.swtDefaults().generateLayout(parent);
		
		getViewSite().getPage().addPostSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getViewSite().getPage().removePostSelectionListener(this);
		super.dispose();
	}


	@Override
	public void setFocus() {
		parent.setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		text1.setText("text1");
		text2.setText("text 2 -- if you click me at first, I will be replaced by <text 1>!!");
	}

}
