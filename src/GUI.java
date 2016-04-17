import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSlider;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class GUI extends JFrame {

	private JPanel contentPane;

	private final static String VERSION = Main.VERSION;
	private final int bitchcount = 3;

	private JTextField minatk_Text;
	private JTextField ignore_Text;
	private JTextField result_Text;
	private JTextField price_Text;
	private JList char_List;
	private JList sort_List;
	private JList cpu_List;
	private JCheckBox noinfo_Check;
	private JCheckBox nocsv_Check;
	private JTextField minmatk_Text;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

		String output ="";
		String cpu = "";
		String pre = "";
		String suf = "";

		if (cpu_List.getSelectedIndex() > 0) {
			cpu = " -f " + cpu_List.getSelectedValue();
		}
		output += pre + suf + cpu;

		if (noinfo_Check.isSelected()) {
			output += " -noinfo";
		}
		if (nocsv_Check.isSelected()) {
			output += " -nocsv";
		}

		if (minatk_Text.getText().length() > 0) {
			output += " -atk " + minatk_Text.getText().trim();
		}
		if (minmatk_Text.getText().length() > 0) {
			output += " -matk " + minmatk_Text.getText().trim();
		}
		if (ignore_Text.getText().length() > 0) {
			output += " -i \"" + ignore_Text.getText().trim().replace(" ", "")
					+ "\"";
		}
		if (price_Text.getText().length() > 0) {
			output += " -price " + price_Text.getText().trim();
		}
		if (result_Text.getText().length() > 0) {
			output += " -r " + result_Text.getText().trim();
		}

		System.out.println(output);
		String[] args = output.split(" ");

		try {
			String x = "cmd.exe /c start java -jar VindictusItemComparer.jar "
					+ output;
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				System.out.println(x);
				Process p = Runtime.getRuntime().exec(x, null,
						new File(System.getProperty("user.dir")));
				p.waitFor();
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"Error. Other OS other than Windows not supported yet.\nUse \n"
								+ x + "\nto run the programm via console.");
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

		setTitle("VIC Starter GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);

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
		panel_1.setBorder(new TitledBorder(null, "Main", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.add(panel_1);

		char_List = new JList();
		panel_1.add(char_List);
		char_List.setToolTipText("Sets");
		char_List.setModel(new AbstractListModel() {
			String[] values = new String[] { "arisha", "evie", "fiona", "hurk",
					"kai", "karok", "lann", "lynn", "vella" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		char_List.setSelectedIndex(0);

		sort_List = new JList();
		panel_1.add(sort_List);
		sort_List.setToolTipText("Sort by");
		sort_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sort_List.setModel(new AbstractListModel() {
			String[] values = new String[] {"crit", "hp", "def"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		sort_List.setSelectedIndex(0);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Restrictions",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);

		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));

		JPanel panel_10 = new JPanel();
		panel_12.add(panel_10);
		panel_10.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Min atk",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0,
						0, 0)));

		minatk_Text = new JTextField();
		minatk_Text.setToolTipText("Minimum atk the sets must have");
		panel_10.add(minatk_Text);
		minatk_Text.setColumns(5);

		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Min matk",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0,
						0, 0)));
		panel_12.add(panel_16);

		minmatk_Text = new JTextField();
		minmatk_Text.setToolTipText("Minimum matk the sets must have");
		minmatk_Text.setColumns(5);
		panel_16.add(minmatk_Text);

		JPanel panel_11 = new JPanel();
		panel_12.add(panel_11);
		panel_11.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Price",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0,
						0, 0)));

		price_Text = new JTextField();
		price_Text.setToolTipText("Maximum price (10 = 10million)");
		price_Text.setColumns(5);
		panel_11.add(price_Text);

		JPanel panel_4 = new JPanel();
		panel_12.add(panel_4);
		panel_4.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Ignore",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, Color.BLACK));

		ignore_Text = new JTextField();
		panel_4.add(ignore_Text);
		ignore_Text
				.setToolTipText("Sets containing those words will be ignored. Use comma for more words. E.g. arma,raider");
		ignore_Text.setColumns(5);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "Min atk",
						TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0,
								0, 0)));
		panel_5.add(panel_7);
		
		textField = new JTextField();
		textField.setToolTipText("Minimum atk the sets must have");
		textField.setColumns(5);
		panel_7.add(textField);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "Min matk",
						TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0,
								0, 0)));
		panel_5.add(panel_8);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Minimum matk the sets must have");
		textField_1.setColumns(5);
		panel_8.add(textField_1);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "Price",
						TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0,
								0, 0)));
		panel_5.add(panel_13);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Maximum price (10 = 10million)");
		textField_2.setColumns(5);
		panel_13.add(textField_2);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "Ignore",
						TitledBorder.LEADING, TitledBorder.BOTTOM, null, Color.BLACK));
		panel_5.add(panel_15);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Sets containing those words will be ignored. Use comma for more words. E.g. arma,raider");
		textField_3.setColumns(5);
		panel_15.add(textField_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Other", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.add(panel_3);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "CPU", TitledBorder.LEADING,
				TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		panel_3.add(panel_6);

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
		panel_3.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.Y_AXIS));

		JPanel panel_9 = new JPanel();
		panel_14.add(panel_9);
		panel_9.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Results",
				TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(0,
						0, 0)));

		result_Text = new JTextField();
		result_Text.setText("30");
		panel_9.add(result_Text);
		result_Text.setToolTipText("Amound of results. (Default = 30)");
		result_Text.setColumns(5);

		noinfo_Check = new JCheckBox("noinfo");
		panel_14.add(noinfo_Check);
		noinfo_Check.setToolTipText("Dont write extra info");
		noinfo_Check.setSelected(true);

		nocsv_Check = new JCheckBox("nocsv");
		nocsv_Check.setToolTipText("Dont write extra info");
		nocsv_Check.setSelected(true);
		panel_14.add(nocsv_Check);

		JLabel lblPic = new JLabel("");
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setIcon(new ImageIcon(
				GUI.class
						.getResource("/covergirls/covergirl"
								+ getCovergirl() + ".jpg")));
		splitPane.setLeftComponent(lblPic);
	}
}