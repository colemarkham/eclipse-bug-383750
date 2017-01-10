package bug383750;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SampleApp {

	private static Text text1;
	private static Text text2;
	private static List list;

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Bug 3833750");
		createContents(shell);
		shell.open();
		while(!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private static void createContents(Shell shell) {
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		list = new List(shell, SWT.BORDER | SWT.SINGLE);
		list.setItems(new String[]{"Click to reset text"});
		list.select(0);
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text1.setText("text 1");
				text2.setText("text 2");
			}
		});
		Composite wrapper = new Composite(shell, SWT.NONE);
		wrapper.setLayout(new FillLayout());
		Composite parent = new Composite(wrapper, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.fill = true;
		parent.setLayout(rowLayout);
		
		Label label = new Label(parent, SWT.NONE);
		label.setText("If you click <text 2> first, it's text will be replaced with <text 1>!!");
		
		text1 = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text1.setLayoutData(new RowData(300, SWT.DEFAULT));
		text2 = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.RIGHT);
		
//		// Simulate View activation and ViewPart.setFocus() in the workbench
		wrapper.addListener(SWT.Activate, event -> {
			parent.setFocus();
		});
		
		text1.setText("text 1");
		text2.setText("text 2");
	}

}
