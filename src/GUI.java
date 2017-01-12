import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
	private static JTable table;
	private static JTextField homeScore;
	private static JTextField awayScore;
	private static JTextField date;
	private static JTextField season;
	private static JTextField matchId;
	private static JTextField tfSeasonUp;
	private static JTextField tfDateUp;
	private static JTextField tfAwayUp;
	private static JTextField tfHomeUp;
	private static JTextField matchIdDelete;
	private static JTable tableFinal;

	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("KickIt");
        frame.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		Result.closeConnection();
        	}
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        
 /* Columns for View data table */
        
        String[] cols = {
        		"MatchID",
        		"League",
        		"Country",
        		"Season",
        		"Match Date",
        		"Home Team",
        		"Away Team",
        		"Home Score",
        		"Away Score",
        };
        
        Object[][] data = Result.getMatches();
        DefaultTableModel dataModel = new DefaultTableModel(data, cols);
        table = new JTable(dataModel);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
        
       
        
        
        JPanel viewPanel = new JPanel();
        tabbedPane.addTab("View", null, viewPanel, null);
        GridBagLayout gbl_viewPanel = new GridBagLayout();
        gbl_viewPanel.columnWidths = new int[]{0, 0};
        gbl_viewPanel.rowHeights = new int[]{0, 0, 0};
        gbl_viewPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_viewPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        viewPanel.setLayout(gbl_viewPanel);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        viewPanel.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JLabel lblSearchBy = new JLabel("Search By");
        GridBagConstraints gbc_lblSearchBy = new GridBagConstraints();
        gbc_lblSearchBy.insets = new Insets(0, 0, 5, 5);
        gbc_lblSearchBy.gridx = 0;
        gbc_lblSearchBy.gridy = 0;
        panel.add(lblSearchBy, gbc_lblSearchBy);
        lblSearchBy.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        
        JLabel lblTeam = new JLabel("Team");
        GridBagConstraints gbc_lblTeam = new GridBagConstraints();
        gbc_lblTeam.insets = new Insets(0, 0, 5, 5);
        gbc_lblTeam.gridx = 0;
        gbc_lblTeam.gridy = 1;
        panel.add(lblTeam, gbc_lblTeam);
        
        JLabel lblCountry = new JLabel("Country    ");
        GridBagConstraints gbc_lblCountry = new GridBagConstraints();
        gbc_lblCountry.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
        gbc_lblCountry.gridx = 2;
        gbc_lblCountry.gridy = 1;
        panel.add(lblCountry, gbc_lblCountry);
        
        JLabel lblLeague = new JLabel("League  ");
        GridBagConstraints gbc_lblLeague = new GridBagConstraints();
        gbc_lblLeague.insets = new Insets(0, 0, 5, 5);
        gbc_lblLeague.gridx = 4;
        gbc_lblLeague.gridy = 1;
        panel.add(lblLeague, gbc_lblLeague);
        
        JLabel lblDate = new JLabel("Date");
        GridBagConstraints gbc_lblDate = new GridBagConstraints();
        gbc_lblDate.insets = new Insets(0, 0, 5, 5);
        gbc_lblDate.gridx = 6;
        gbc_lblDate.gridy = 1;
        panel.add(lblDate, gbc_lblDate);
        
        JComboBox comboTeamSearch = new JComboBox(Result.getTeam());
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.anchor = GridBagConstraints.NORTH;
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 2;
        panel.add(comboTeamSearch, gbc_comboBox);
        
        JComboBox comboCountrySearch = new JComboBox(Result.getCountry());
        GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
        gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_1.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox_1.gridx = 2;
        gbc_comboBox_1.gridy = 2;
        panel.add(comboCountrySearch, gbc_comboBox_1);
        
        JComboBox comboLeagueSearch = new JComboBox(Result.getLeague());
        GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
        gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_2.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox_2.gridx = 4;
        gbc_comboBox_2.gridy = 2;
        panel.add(comboLeagueSearch, gbc_comboBox_2);
        
        JComboBox comboDateSearch = new JComboBox(Result.getDates());
        GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
        gbc_comboBox_3.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_3.gridx = 6;
        gbc_comboBox_3.gridy = 2;
        panel.add(comboDateSearch, gbc_comboBox_3);
        
        ChangeListener changeListener = new ChangeListener() {
   	     public void stateChanged(ChangeEvent changeEvent) {
   	      JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
   	      int index = sourceTabbedPane.getSelectedIndex();
   			if (index == 0) {
   				String[] cols = {
   		        		"MatchID",
   		        		"League",
   		        		"Country",
   		        		"Season",
   		        		"Match Date",
   		        		"Home Team",
   		        		"Away Team",
   		        		"Home Score",
   		        		"Away Score",
   	        		};

   					//call query and get new table values
   					String [][] newData = Result.getMatches();
   	        		DefaultTableModel dataModel = new DefaultTableModel(newData, cols);
   	        		table.setModel(dataModel);
   	        		
   	        		DefaultComboBoxModel lmodel = new DefaultComboBoxModel(Result.getLeague());
   	        		comboLeagueSearch.setModel(lmodel); 
   	        		
   	        		DefaultComboBoxModel cmodel = new DefaultComboBoxModel(Result.getCountry());
   	        		comboCountrySearch.setModel(cmodel);
   	        		
   	        		DefaultComboBoxModel dmodel = new DefaultComboBoxModel(Result.getDates());
   	        		comboDateSearch.setModel(dmodel);
   	        		
   	        		DefaultComboBoxModel tmodel = new DefaultComboBoxModel(Result.getTeam());
   	        		comboTeamSearch.setModel(tmodel);
   			}
   	     }
       };
       tabbedPane.addChangeListener(changeListener);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String league = (String)comboLeagueSearch.getSelectedItem();
        		String country = (String)comboCountrySearch.getSelectedItem();
        		String team = (String)comboTeamSearch.getSelectedItem();
        		String date = (String)comboDateSearch.getSelectedItem();
        		String [][] newData = Result.filterMatches(team, country, league, date);
        		String[] cols = {
	        		"MatchID",
	        		"League",
	        		"Country",
	        		"Season",
	        		"Match Date",
	        		"Home Team",
	        		"Away Team",
	        		"Home Score",
	        		"Away Score",
        		};

				//call query and get new table values
        		DefaultTableModel dataModel = new DefaultTableModel(newData, cols);
        		table.setModel(dataModel);
        	}
        });
        GridBagConstraints gbc_btnSearch = new GridBagConstraints();
        gbc_btnSearch.gridx = 8;
        gbc_btnSearch.gridy = 2;
        panel.add(btnSearch, gbc_btnSearch);
        
        
        
        
        
        
       
        
        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        viewPanel.add(scrollPane, gbc_scrollPane);
        
        scrollPane.setViewportView(table);
        
        
        JPanel statsPanel = new JPanel();
        tabbedPane.addTab("Stats", null, statsPanel, null);
        statsPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        statsPanel.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(null);
        
        JLabel lblAveragePerTeam = new JLabel("Average Per Team");
        lblAveragePerTeam.setBounds(18, 22, 148, 16);
        panel_1.add(lblAveragePerTeam);
        
        JLabel avgOutput = new JLabel("");
        avgOutput.setBounds(303, 46, 61, 16);
        panel_1.add(avgOutput);
        
        JComboBox comboavgTeam = new JComboBox(Result.getTeam());
        comboavgTeam.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JComboBox cb = (JComboBox)e.getSource();
        		String team = (String)cb.getSelectedItem();
        		
        		//call query with team string
        		avgOutput.setText(Result.averageSelect(team));
        		//set avgOutput 
        	}
        });
        comboavgTeam.setBounds(18, 42, 148, 27);
        panel_1.add(comboavgTeam);
        
        
        
        JLabel lblPercentageOfGames = new JLabel("Percentage of games won at home vs away");
        lblPercentageOfGames.setBounds(18, 91, 289, 16);
        panel_1.add(lblPercentageOfGames);
        
        JLabel lblHome = new JLabel("Home");
        lblHome.setBounds(303, 134, 61, 16);
        panel_1.add(lblHome);
        
        JLabel lblAway = new JLabel("Away");
        lblAway.setBounds(408, 134, 61, 16);
        panel_1.add(lblAway);
        
        JLabel homeOutput = new JLabel("");
        homeOutput.setBounds(303, 119, 61, 16);
        panel_1.add(homeOutput);
        
        JLabel awayOutput = new JLabel("");
        awayOutput.setBounds(408, 119, 61, 16);
        panel_1.add(awayOutput);
        
        JComboBox comboPercentTeam = new JComboBox(Result.getTeam());
        comboPercentTeam.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JComboBox cb = (JComboBox)e.getSource();
        		String team = (String)cb.getSelectedItem();
        		
        		//call query with team string
        		String[] percents = Result.percentageSelect(team);
        		//set homeOutput
        		homeOutput.setText(percents[0]);
        		//set awayOutput
        		awayOutput.setText(percents[1]);
        	}
        });
        comboPercentTeam.setBounds(18, 123, 148, 27);
        panel_1.add(comboPercentTeam);
        
        JLabel winnerOutput = new JLabel("");
        winnerOutput.setBounds(560, 275, 213, 16);
        panel_1.add(winnerOutput);
        
        JLabel lblFinalStandings = new JLabel("Final Standings");
        lblFinalStandings.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblFinalStandings.setBounds(22, 175, 199, 16);
        panel_1.add(lblFinalStandings);
        
        JComboBox cbLeagueFinal = new JComboBox(Result.getLeague());
        cbLeagueFinal.setBounds(18, 203, 168, 27);
        panel_1.add(cbLeagueFinal);
        
        JComboBox cbSeasonFinal = new JComboBox(Result.getSeason());
        cbSeasonFinal.setBounds(303, 203, 140, 27);
        panel_1.add(cbSeasonFinal);
        
        String fcols [] = {"TeamName", "TeamShort", "Wins", "Losses", "Ties", "Points"};
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(21, 242, 737, 240);
        panel_1.add(scrollPane_1);
        String [][] fdata = {{}};
        DefaultTableModel model = new DefaultTableModel(fdata,fcols);
        tableFinal = new JTable(model);
        scrollPane_1.setViewportView(tableFinal);
        tableFinal.setBackground(Color.WHITE);
        
        JButton btnGetStanding = new JButton("Get Standing");
        btnGetStanding.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String[][] fdata = Result.finalSelect(cbLeagueFinal.getSelectedItem().toString(),cbSeasonFinal.getSelectedItem().toString());
                DefaultTableModel dataModel = new DefaultTableModel(fdata, fcols);
                tableFinal.setModel(dataModel);

                
        	}
        });
        btnGetStanding.setBounds(543, 202, 117, 29);
        panel_1.add(btnGetStanding);
        
        JPanel modifyPanel = new JPanel();
        tabbedPane.addTab("Modify", null, modifyPanel, null);
        modifyPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        modifyPanel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Insert Match");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblNewLabel.setBounds(6, 6, 109, 16);
        panel_2.add(lblNewLabel);
        
        JLabel lblCountry_1 = new JLabel("Country");
        lblCountry_1.setBounds(16, 34, 61, 16);
        panel_2.add(lblCountry_1);
        
        JLabel lblLeague_1 = new JLabel("League");
        lblLeague_1.setBounds(170, 34, 61, 16);
        panel_2.add(lblLeague_1);
        
        JLabel lblHomeTeam = new JLabel("Home Team");
        lblHomeTeam.setBounds(323, 34, 90, 16);
        panel_2.add(lblHomeTeam);
        
        JLabel lblAwayTeam = new JLabel("Away Team");
        lblAwayTeam.setBounds(489, 34, 102, 16);
        panel_2.add(lblAwayTeam);
        
        JComboBox comboCountry = new JComboBox(Result.getCountry());
        comboCountry.setBounds(6, 56, 109, 27);
        panel_2.add(comboCountry);
        
        JComboBox comboLeague = new JComboBox(Result.getLeague());
        comboLeague.setBounds(159, 56, 115, 27);
        panel_2.add(comboLeague);
        
        JComboBox comboHome = new JComboBox(Result.getTeam());
        comboHome.setBounds(323, 56, 115, 27);
        panel_2.add(comboHome);
        
        JComboBox comboAway = new JComboBox(Result.getTeam());
        comboAway.setBounds(487, 56, 125, 27);
        panel_2.add(comboAway);
        
        JLabel lblHomeScore = new JLabel("Home Score");
        lblHomeScore.setBounds(16, 95, 109, 16);
        panel_2.add(lblHomeScore);
        
        JLabel lblAwayScore = new JLabel("Away Score");
        lblAwayScore.setBounds(169, 95, 90, 16);
        panel_2.add(lblAwayScore);
        
        JLabel lblDate_1 = new JLabel("Date");
        lblDate_1.setBounds(323, 95, 109, 16);
        panel_2.add(lblDate_1);
        
        JLabel lblSeason = new JLabel("Season");
        lblSeason.setBounds(450, 95, 109, 16);
        panel_2.add(lblSeason);
        
        homeScore = new JTextField();
        homeScore.setBounds(16, 123, 61, 28);
        panel_2.add(homeScore);
        homeScore.setColumns(10);
        
        awayScore = new JTextField();
        awayScore.setColumns(10);
        awayScore.setBounds(170, 123, 61, 28);
        panel_2.add(awayScore);
        
        date = new JTextField();
        date.setColumns(10);
        date.setBounds(318, 123, 75, 28);
        panel_2.add(date);
        
        season = new JTextField();
        season.setColumns(10);
        season.setBounds(450, 123, 75, 28);
        panel_2.add(season);
        
        JLabel lblUpdate = new JLabel("Update");
        lblUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblUpdate.setBounds(6, 174, 109, 16);
        panel_2.add(lblUpdate);
        
        JLabel lblMatchId = new JLabel("Match ID");
        lblMatchId.setBounds(16, 202, 61, 16);
        panel_2.add(lblMatchId);
        
        matchId = new JTextField();
        matchId.setBounds(97, 196, 75, 28);
        panel_2.add(matchId);
        matchId.setColumns(10);
        
        
        
        JLabel label = new JLabel("Country");
        label.setBounds(26, 230, 61, 16);
        panel_2.add(label);
        
        JComboBox comboCountryUp = new JComboBox(Result.getCountry());
        comboCountryUp.setBounds(16, 252, 109, 27);
        panel_2.add(comboCountryUp);
        
        JLabel label_1 = new JLabel("League");
        label_1.setBounds(180, 230, 61, 16);
        panel_2.add(label_1);
        
        JComboBox comboLeagueUp = new JComboBox(Result.getLeague());
        comboLeagueUp.setBounds(169, 252, 115, 27);
        panel_2.add(comboLeagueUp);
        
        JLabel label_2 = new JLabel("Home Team");
        label_2.setBounds(333, 230, 90, 16);
        panel_2.add(label_2);
        
        JComboBox comboHomeUp = new JComboBox(Result.getTeam());
        comboHomeUp.setBounds(333, 252, 115, 27);
        panel_2.add(comboHomeUp);
        
        JLabel label_3 = new JLabel("Away Team");
        label_3.setBounds(499, 230, 102, 16);
        panel_2.add(label_3);
        
        JComboBox comboAwayUp = new JComboBox(Result.getTeam());
        comboAwayUp.setBounds(497, 252, 125, 27);
        panel_2.add(comboAwayUp);
        
        JLabel label_4 = new JLabel("Season");
        label_4.setBounds(460, 291, 109, 16);
        panel_2.add(label_4);
        
        tfSeasonUp = new JTextField();
        tfSeasonUp.setColumns(10);
        tfSeasonUp.setBounds(460, 319, 75, 28);
        panel_2.add(tfSeasonUp);
        
        tfDateUp = new JTextField();
        tfDateUp.setColumns(10);
        tfDateUp.setBounds(328, 319, 75, 28);
        panel_2.add(tfDateUp);
        
        JLabel label_5 = new JLabel("Date");
        label_5.setBounds(333, 291, 109, 16);
        panel_2.add(label_5);
        
        tfAwayUp = new JTextField();
        tfAwayUp.setColumns(10);
        tfAwayUp.setBounds(180, 319, 61, 28);
        panel_2.add(tfAwayUp);
        
        JLabel label_6 = new JLabel("Away Score");
        label_6.setBounds(179, 291, 90, 16);
        panel_2.add(label_6);
        
        tfHomeUp = new JTextField();
        tfHomeUp.setColumns(10);
        tfHomeUp.setBounds(26, 319, 61, 28);
        panel_2.add(tfHomeUp);
        
        JLabel label_7 = new JLabel("Home Score");
        label_7.setBounds(26, 291, 109, 16);
        panel_2.add(label_7);
        
        JLabel lblUpdateResult = new JLabel("");
        lblUpdateResult.setBounds(16, 359, 691, 16);
        panel_2.add(lblUpdateResult);
        
        JButton btnGetMatch = new JButton("get match");
        btnGetMatch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String match = matchId.getText();
        		
        		//get match values back
        		String[] result = Result.getMatchInfo(match);
        		
        		if (result.length > 1) 
        		{
	        		//l.Name, c.Name, m.Season, m.MatchDate, t.TeamName, t2.TeamName, m.HomeScore, m.AwayScore
	        		comboLeagueUp.setSelectedItem(result[0]);
	        		comboCountryUp.setSelectedItem(result[1]);
	        		comboHomeUp.setSelectedItem(result[4]);
	        		comboAwayUp.setSelectedItem(result[5]);
	        		tfSeasonUp.setText(result[2]);
	        		tfDateUp.setText(result[3]);
	        		tfHomeUp.setText(result[6]);
	        		tfAwayUp.setText(result[7]);
        		}
        		else 
        		{
        			lblUpdateResult.setText(result[0]);
        		}
        		
        	}
        });
        btnGetMatch.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        btnGetMatch.setBounds(205, 197, 90, 29);
        panel_2.add(btnGetMatch);
        
        JLabel lblDelet = new JLabel("Delete");
        lblDelet.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblDelet.setBounds(6, 395, 109, 16);
        panel_2.add(lblDelet);
        
        JLabel label_8 = new JLabel("Match ID");
        label_8.setBounds(16, 429, 61, 16);
        panel_2.add(label_8);
        
        matchIdDelete = new JTextField();
        matchIdDelete.setColumns(10);
        matchIdDelete.setBounds(97, 423, 75, 28);
        panel_2.add(matchIdDelete);
        
        JLabel lblDeleteResult = new JLabel("");
        lblDeleteResult.setBounds(16, 468, 432, 16);
        panel_2.add(lblDeleteResult);
        
        JButton btnDelete = new JButton("delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String match = matchIdDelete.getText();
        		String result = Result.deleteFromMatches(match);
        		lblDeleteResult.setText(result);
        	}
        });
        btnDelete.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        btnDelete.setBounds(205, 424, 90, 29);
        panel_2.add(btnDelete);
        
        JButton btnUpdate = new JButton("update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String result = Result.updateMatches(matchId.getText(), comboCountryUp.getSelectedItem().toString(), 
        				comboLeagueUp.getSelectedItem().toString(), 
        				tfSeasonUp.getText(), tfDateUp.getText(), comboHomeUp.getSelectedItem().toString(), 
        				comboAwayUp.getSelectedItem().toString(), 
        				tfHomeUp.getText(), tfAwayUp.getText());
        		
        		lblUpdateResult.setText(result);
        		
        	}
        });
        btnUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        btnUpdate.setBounds(643, 321, 90, 29);
        panel_2.add(btnUpdate);
        
        JLabel lblInsertResult = new JLabel("");
        lblInsertResult.setBounds(431, 163, 331, 16);
        panel_2.add(lblInsertResult);
        
        JButton btnInsert = new JButton("insert");
        btnInsert.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String league = (String)comboLeague.getSelectedItem();
        		String country = (String)comboCountry.getSelectedItem();
        		String homeTeam = (String)comboHome.getSelectedItem();
        		String awayTeam = (String)comboHome.getSelectedItem();
        		String seasonStr = season.getText();
        		String hscore = homeScore.getText();
        		String ascore = awayScore.getText();
        		String dateStr = date.getText();
        		
        		//call insert with strings
        		String result = Result.insertMatch(country, league, seasonStr, dateStr, homeTeam, awayTeam, hscore, ascore);
        		
        		lblInsertResult.setText(result);
        		
        	}
        });
        btnInsert.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        btnInsert.setBounds(643, 125, 90, 29);
        panel_2.add(btnInsert);
        
        JButton btnCloseConn = new JButton("close conn");
        btnCloseConn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Result.closeConnection();
        	}
        });
        btnCloseConn.setBounds(6, 496, 117, 29);
        panel_2.add(btnCloseConn);
        
        JButton btnResetData = new JButton("Reset Data");
        btnResetData.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Result.resetConnection();
        	}
        });
        btnResetData.setBounds(645, 455, 117, 29);
        panel_2.add(btnResetData);
        
        
        
        
        
        
        frame.setVisible(true);
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    } 
    
    protected static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
