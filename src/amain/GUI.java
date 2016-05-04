package amain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import inport.CSVImport;
import modell.SoulSet;

public class GUI extends JFrame {

	private JPanel contentPane;

	private final static String VERSION = Main.VERSION;
	private final int bitchcount = 3;

	private JTextField mincrit_Text;
	private JTextField result_Text;
	private JList char_List;
	private JList sort_List;
	private JList cpu_List;
	private JCheckBox noinfo_Check;
	private JCheckBox nocsv_Check;
	private JTextField minaccu_Text;
	private JTextField minhp_Text;
	private JTextField mindef_Text;
	private JComboBox buff1Combo = new JComboBox();
	private JComboBox buff2Box = new JComboBox(SoulSet.getAllSets());
	private JComboBox buff1Box = new JComboBox(SoulSet.getAllSets());
	private JComboBox buff2Combo = new JComboBox();
	private JCheckBox critCheck = new JCheckBox("crit only");
	private JCheckBox ecrit_max_Check = new JCheckBox("max");
	private JSpinner ecrit_max_Spinner = new JSpinner();
	private JTextField mincdef_Text;
	private JComboBox enchant_all_Box = new JComboBox();
	JList not_List = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		CSVImport.importSoulSets();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void calc() {

		String output = "";
		String cpu = "";

		String sort = " -sort " + sort_List.getSelectedValue().toString();

		String sets = " -sets \"";

		for (Object s : char_List.getSelectedValues()) {
			sets += ((SoulSet) s) + " ";
		}
		sets = sets.trim();
		sets += "\"";

		if (cpu_List.getSelectedIndex() > 0) {
			cpu = " -f " + cpu_List.getSelectedValue();
		}
		output += sets + sort + cpu;

		if (buff1Combo.getSelectedIndex() > 0) {
			output += " -aset \"" + buff1Box.getSelectedItem().toString() + "\" -acount "
					+ buff1Combo.getSelectedItem();
		}
		if (buff2Box.isEnabled() && buff2Combo.getSelectedIndex() > 0) {
			output += " -bset \"" + buff2Box.getSelectedItem().toString() + "\" -bcount "
					+ buff2Combo.getSelectedItem();
		}

		if (noinfo_Check.isSelected()) {
			output += " -noinfo";
		}
		if (nocsv_Check.isSelected()) {
			output += " -nocsv";
		}

		if (critCheck.isSelected()) {
			output += " -critonly ";
		}

		if (mincrit_Text.getText().length() > 0) {
			output += " -crit " + mincrit_Text.getText().trim();
		}
		if (minaccu_Text.getText().length() > 0) {
			output += " -accu " + minaccu_Text.getText().trim();
		}
		if (minhp_Text.getText().length() > 0) {
			output += " -hp " + minhp_Text.getText().trim();
		}
		if (mindef_Text.getText().length() > 0) {
			output += " -def " + mindef_Text.getText().trim();
		}
		if (mincdef_Text.getText().length() > 0) {
			output += " -cdef " + mincdef_Text.getText().trim();
		}

		if (result_Text.getText().length() > 0) {
			output += " -r " + result_Text.getText().trim();
		}

		if (ecrit_max_Check.isSelected()) {
			output += " -eall \"9999 " + enchant_all_Box.getSelectedItem().toString() + "\"";
		} else {
			if ((int) ecrit_max_Spinner.getValue() > 0) {
				output += " -eall \"" + ecrit_max_Spinner.getValue() + " "
						+ enchant_all_Box.getSelectedItem().toString() + "\"";
				;
			}
		}

		if (!not_List.isSelectionEmpty()) {
			String only = " -not \"";

			for (Object s : not_List.getSelectedValues()) {
				only += s;
			}
			only += "\"";
			output += only;
		}

		System.out.println(output);
		String[] args = output.split(" ");

		try {
			String x = "cmd.exe /c start java -jar BNS_Comparer.jar " + output;
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				System.out.println(x);
				Process p = Runtime.getRuntime().exec(x, null, new File(System.getProperty("user.dir")));
				p.waitFor();
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"Error. Other OS other than Windows not supported yet.\nUse \n" + x
								+ "\nto run the programm via console.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getCovergirl() {
		int x = (int) (Math.random() * bitchcount + 1);
		return x;
	}

	/**
	 * Create the frame.
	 */
	public GUI() {

		setTitle("BNS_Comparer GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 670);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(new JFrame(), VERSION);

			}
		});
		menuBar.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.9);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);

		JButton btnCalc = new JButton("Calc");
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calc();
			}
		});
		btnCalc.setFont(new Font("Tahoma", Font.BOLD, 24));
		splitPane_1.setRightComponent(btnCalc);

		JPanel panel = new JPanel();
		splitPane_1.setLeftComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sets", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_1);

		char_List = new JList(SoulSet.getAllSets());
		panel_1.add(char_List);
		char_List.setToolTipText("Sets");

		char_List.setSelectedIndex(0);

		JPanel panel_22 = new JPanel();
		panel_22.setAlignmentX(0.0f);
		panel_22.setBorder(new TitledBorder(null, "Sort by", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_22);

		JPanel panel_27 = new JPanel();
		panel_22.add(panel_27);
		panel_27.setLayout(new BoxLayout(panel_27, BoxLayout.Y_AXIS));

		sort_List = new JList();
		panel_27.add(sort_List);
		sort_List.setToolTipText("Sort by");
		sort_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sort_List.setModel(new AbstractListModel() {
			String[] values = new String[] { "crit", "hp", "def", "critdef" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		sort_List.setSelectedIndex(0);

		JLabel label = new JLabel("  ");
		panel_27.add(label);

		JPanel panel_15 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_15.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_15.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buffs", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_15);

		JPanel panel_19 = new JPanel();
		panel_15.add(panel_19);
		panel_19.setLayout(new BoxLayout(panel_19, BoxLayout.Y_AXIS));

		JPanel panel_18 = new JPanel();
		panel_19.add(panel_18);
		panel_18.setLayout(new BoxLayout(panel_18, BoxLayout.Y_AXIS));

		JPanel panel_23 = new JPanel();
		panel_18.add(panel_23);
		JTextArea buff1Text = new JTextArea();
		JTextArea buff2Text = new JTextArea();
		buff1Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buff1Combo.getSelectedIndex() == 1) {
					buff1Text.setText(((SoulSet) buff1Box.getSelectedItem()).getBuff(3));
				}
				if (buff1Combo.getSelectedIndex() == 2) {
					buff1Text.setText(((SoulSet) buff1Box.getSelectedItem()).getBuff(5));
				}
				if (buff1Combo.getSelectedIndex() == 3) {
					buff1Text.setText(((SoulSet) buff1Box.getSelectedItem()).getBuff(8));
				}
			}
		});

		panel_23.add(buff1Combo);
		buff1Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (buff1Combo.getSelectedIndex() == 1 || buff1Combo.getSelectedIndex() == 2) {
					buff2Box.setEnabled(true);
					buff2Combo.setEnabled(true);

				} else {
					buff2Box.setEnabled(false);
					buff2Combo.setEnabled(false);
				}
				if (buff1Combo.getSelectedIndex() == 1) {
					buff1Text.setText(((SoulSet) buff1Box.getSelectedItem()).getBuff(3));
				}
				if (buff1Combo.getSelectedIndex() == 2) {
					buff1Text.setText(((SoulSet) buff1Box.getSelectedItem()).getBuff(5));
				}
				if (buff1Combo.getSelectedIndex() == 3) {
					buff1Text.setText(((SoulSet) buff1Box.getSelectedItem()).getBuff(8));
				}
			}
		});
		buff1Combo.setFont(new Font("Tahoma", Font.PLAIN, 8));
		buff1Combo.setModel(new DefaultComboBoxModel(new String[] { "None", "3", "5", "8" }));
		panel_23.add(buff1Box);

		buff2Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buff2Combo.getSelectedIndex() == 1) {
					buff2Text.setText(((SoulSet) buff2Box.getSelectedItem()).getBuff(3));
				}
				if (buff2Combo.getSelectedIndex() == 2) {
					buff2Text.setText(((SoulSet) buff2Box.getSelectedItem()).getBuff(5));
				}
				if (buff2Combo.getSelectedIndex() == 3) {
					buff2Text.setText(((SoulSet) buff2Box.getSelectedItem()).getBuff(8));
				}
			}
		});

		JPanel panel_24 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_24.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		panel_18.add(panel_24);

		buff1Text.setWrapStyleWord(true);
		buff1Text.setTabSize(10);
		buff1Text.setEditable(false);
		buff1Text.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buff1Text.setText("xxx");
		panel_24.add(buff1Text);

		JPanel panel_17 = new JPanel();
		panel_19.add(panel_17);
		panel_17.setLayout(new BoxLayout(panel_17, BoxLayout.Y_AXIS));

		JPanel panel_20 = new JPanel();
		panel_17.add(panel_20);

		buff2Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buff2Combo.getSelectedIndex() == 1) {
					buff2Text.setText(((SoulSet) buff2Box.getSelectedItem()).getBuff(3));
				}
				if (buff2Combo.getSelectedIndex() == 2) {
					buff2Text.setText(((SoulSet) buff2Box.getSelectedItem()).getBuff(5));
				}
				if (buff2Combo.getSelectedIndex() == 3) {
					buff2Text.setText(((SoulSet) buff2Box.getSelectedItem()).getBuff(8));
				}
			}
		});
		buff2Combo.setFont(new Font("Tahoma", Font.PLAIN, 8));
		buff2Combo.setModel(new DefaultComboBoxModel(new String[] { "None", "3", "5" }));
		panel_20.add(buff2Combo);
		buff2Box.setSelectedIndex(1);
		panel_20.add(buff2Box);

		JPanel panel_25 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_25.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		panel_17.add(panel_25);

		buff2Text.setWrapStyleWord(true);
		buff2Text.setText("yyy");
		buff2Text.setTabSize(10);
		buff2Text.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buff2Text.setEditable(false);
		panel_25.add(buff2Text);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Enchants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(panel_13);

		enchant_all_Box.setModel(new DefaultComboBoxModel(new String[] { "crit", "def", "critdef" }));
		enchant_all_Box.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_13.add(enchant_all_Box);

		ecrit_max_Check.setSelected(true);
		panel_13.add(ecrit_max_Check);

		ecrit_max_Spinner.setModel(new SpinnerNumberModel(0, 0, 9999, 10));
		panel_13.add(ecrit_max_Spinner);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Other", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panel_26 = new JPanel();
		panel_3.add(panel_26);

		JPanel panel_6 = new JPanel();
		panel_26.add(panel_6);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CPU", TitledBorder.LEADING,
				TitledBorder.BOTTOM, null, new Color(0, 0, 0)));

		cpu_List = new JList();
		panel_6.add(cpu_List);
		cpu_List.setToolTipText("CPUs to use");
		cpu_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cpu_List.setModel(new AbstractListModel() {
			String[] values = new String[] { "ALL ", "1", "2", "3", "4" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		cpu_List.setSelectedIndex(0);

		JPanel panel_14 = new JPanel();
		panel_26.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.Y_AXIS));

		JPanel panel_9 = new JPanel();
		panel_14.add(panel_9);
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Results", TitledBorder.LEADING,
				TitledBorder.BOTTOM, null, new Color(0, 0, 0)));

		result_Text = new JTextField();
		result_Text.setText("10");
		panel_9.add(result_Text);
		result_Text.setToolTipText("Amound of results. (Default = 30)");
		result_Text.setColumns(5);

		noinfo_Check = new JCheckBox("noinfo");
		noinfo_Check.setEnabled(false);
		panel_14.add(noinfo_Check);
		noinfo_Check.setToolTipText("Dont write extra info");
		noinfo_Check.setSelected(true);

		nocsv_Check = new JCheckBox("nocsv");
		nocsv_Check.setToolTipText("Dont write extra info");
		nocsv_Check.setSelected(true);
		panel_14.add(nocsv_Check);

		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "Restrictions", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_28 = new JPanel();
		panel_28.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "not", TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		panel_2.add(panel_28);

		JLabel label_1 = new JLabel(" ");
		panel_28.add(label_1);

		panel_28.add(not_List);
		not_List.setModel(new AbstractListModel() {
			String[] values = new String[] { "1", "2", "3", "4", "5", "6", "7", "8" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		not_List.setToolTipText("dont use these ss");

		JLabel label_2 = new JLabel(" ");
		panel_28.add(label_2);

		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));

		JPanel panel_10 = new JPanel();
		panel_12.add(panel_10);
		panel_10.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Min crit",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));

		mincrit_Text = new JTextField();
		mincrit_Text.setToolTipText("Minimum atk the sets must have");
		panel_10.add(mincrit_Text);
		mincrit_Text.setColumns(5);

		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Min accu",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		panel_12.add(panel_16);

		minaccu_Text = new JTextField();
		minaccu_Text.setToolTipText("Minimum matk the sets must have");
		minaccu_Text.setColumns(5);
		panel_16.add(minaccu_Text);

		JPanel panel_11 = new JPanel();
		panel_12.add(panel_11);
		panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Min cdef",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));

		mincdef_Text = new JTextField();
		mincdef_Text.setToolTipText("Minimum matk the sets must have");
		mincdef_Text.setColumns(5);
		panel_11.add(mincdef_Text);

		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Min hp", TitledBorder.LEADING,
				TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		panel_5.add(panel_7);

		minhp_Text = new JTextField();
		minhp_Text.setToolTipText("Minimum atk the sets must have");
		minhp_Text.setColumns(5);
		panel_7.add(minhp_Text);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Min def", TitledBorder.LEADING,
				TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		panel_5.add(panel_8);

		mindef_Text = new JTextField();
		mindef_Text.setToolTipText("Minimum matk the sets must have");
		mindef_Text.setColumns(5);
		panel_8.add(mindef_Text);

		JPanel panel_21 = new JPanel();
		panel_5.add(panel_21);

		critCheck.setToolTipText("Only use ss with crit");
		panel_21.add(critCheck);

		JLabel lblPic = new JLabel("");
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setIcon(new ImageIcon(GUI.class.getResource("/covergirls/covergirl" + getCovergirl() + ".jpg")));
		splitPane.setLeftComponent(lblPic);
	}
}
