package bug383750;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "Bug383750Workbench.view";

	private TableViewer viewer;
	
	private class StringLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(Object element) {
			return super.getText(element);
		}

		@Override
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}

	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.getTable().setLinesVisible(true);

		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.setLabelProvider(new StringLabelProvider());

		viewer.getTable().getColumn(0).setWidth(200);
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		
		// Provide the input to the ContentProvider
		viewer.setInput(createInitialDataModel());
		
		getViewSite().setSelectionProvider(viewer);
	}


	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private List<String> createInitialDataModel() {
		List<String> input = new ArrayList<>();
		input.add("One");
		input.add("Two");
		input.add("Three");
		return input;
	}
}