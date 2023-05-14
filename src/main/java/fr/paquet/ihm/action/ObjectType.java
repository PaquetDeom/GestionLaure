package fr.paquet.ihm.action;

import main.MainFrame;

public enum ObjectType {

	SEQUENCE, MODULE, PROGRESSION;

	public Object getObjectVersion() {
		switch (this) {
		case SEQUENCE:
			return MainFrame.getUniqInstance().getSequenceVersion();
		case MODULE:
			return MainFrame.getUniqInstance().getModuleVersion();
		case PROGRESSION:
			return MainFrame.getUniqInstance().getProgressionVersion();

		default:
			return null;
		}
	}

	public boolean isModifiable() {
		switch (this) {
		case SEQUENCE:
			return MainFrame.getUniqInstance().getSequenceVersion().isModifiable();
		case MODULE:
			return MainFrame.getUniqInstance().getModuleVersion().isModifiable();
		case PROGRESSION:
			return MainFrame.getUniqInstance().getProgressionVersion().isModifiable();

		default:
			return false;
		}
	}
}
