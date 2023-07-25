package fr.paquet.ihm.commun.gestionnaire;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import fr.paquet.ihm.style.StyleBorder;

public abstract class ButtomPanel extends JPanel implements ListSelectionListener, ActionListener {

	@SuppressWarnings("serial")
	private class InnerTableModel extends AbstractTableModel {
		@SuppressWarnings("rawtypes")
		List list = null;
		String[] columnName = null;
		@SuppressWarnings("rawtypes")
		Class[] columnClass = null;

		public InnerTableModel(@SuppressWarnings("rawtypes") List list, String[] columnName,
				@SuppressWarnings("rawtypes") Class[] columnClass) {
			this.list = list;
			this.columnName = columnName;
			this.columnClass = columnClass;
		}

		@Override
		public int getRowCount() {
			return (list != null) ? list.size() : 0;
		}

		@Override
		public int getColumnCount() {
			return columnName.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return columnName[columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnClass[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public Object getObject(int i) {
			return list.get(i);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return getListValue(getObject(rowIndex), columnIndex);
		}

		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialogGestion jDialogGestion = null;

	public abstract Object getListValue(Object object, int columnIndex);

	@SuppressWarnings("rawtypes")
	public abstract List getValues();

	public abstract String[] getColumnsName();

	@SuppressWarnings("rawtypes")
	public abstract Class[] getColumnsClass();

	public ButtomPanel(JDialogGestion jDialogGestion) {
		super();

		// ajout du layout
		setLayout(new GridBagLayout());

		// ajout des composants
		setjDialogGestion(jDialogGestion);
		setButtons(new ArrayList<JButton>());

		// ajout des composants au panel
		affiche();

		// attributs du panel
		setBorder(StyleBorder.BORDERPANEL.getBorder());

	}

	public void affiche() {
		removeAll();

		add(getListPanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(getPanelButton(), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		revalidate();
	}

	private PanelButton panelButton = null;

	public PanelButton getPanelButton() {
		if (panelButton == null)
			panelButton = new PanelButton(this, getButtons());
		return panelButton;
	}

	protected List<JButton> buttons = null;

	protected abstract void setButtons(List<JButton> button);

	private List<JButton> getButtons() {
		return buttons;
	}

	InnerTableModel tableModel = null;

	public InnerTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(InnerTableModel tableModel) {
		this.tableModel = tableModel;
	}

	JTable table = null;

	private Component getListPanel() {
		if (table == null) {
			setTableModel(new InnerTableModel(getValues(), getColumnsName(), getColumnsClass()));
			table = new JTable(getTableModel());
			table.setSize(600, 500);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListSelectionModel rowSM = table.getSelectionModel();
			rowSM.addListSelectionListener(this);
		}
		return table;
	}

	public JDialogGestion getjDialogGestion() {
		return jDialogGestion;
	}

	public void setjDialogGestion(JDialogGestion jDialogGestion) {
		this.jDialogGestion = jDialogGestion;
	}

}
