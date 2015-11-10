package com.xAwesom3.ranking.UI.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.xAwesom3.ranking.UI.MainFrame;

public class UIView extends JPanel {
	private static final long		serialVersionUID	= -8942181334582052427L;

	private List<UIVoteComponent>	components			= new ArrayList<UIVoteComponent>();

	public UIView() {
		setBackground(new Color(235, 198, 140));
	}

	public void initComponents() {
		int index = 0;
		int x = 0;
		int y = 0;
		for (x = 0; x < 2; x++) {
			for (y = 0; y < components.size() / 2; y++) {
				components.get(index).setLocation(x * (UIVoteComponent.WIDTH + 20), y * (UIVoteComponent.HEIGHT + 20));
				add(components.get(index));
				components.get(index).resize();
				index++;
			}
		}
		if (components.size() % 2 == 1) {
			components.get(index).setLocation(MainFrame.WIDTH / 2 - components.get(index).width / 2, y * components.get(index).height);
			add(components.get(index));
			components.get(index).resize();
		}
	}

	public void resize() {
		for (UIVoteComponent component : components) {
			component.resize(getWidth(), getHeight());
		}
	}

	public List<UIVoteComponent> getVoteComponents() {
		return components;
	}
}
