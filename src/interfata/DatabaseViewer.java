package interfata;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import controller.*;
import model.*;


public class DatabaseViewer extends JFrame {

    public static final String PANEL_CLIENT = "Client Viewer";
    public static final String PANEL_COMANDA = "Comanda Viewer";
    public static final String PANEL_DETALII_COMANDA = "Detalii Comanda Viewer";
    public static final String PANEL_PRODUS = "Produs Viewer";
    public static final String PANEL_INDEX = "Index Viewer";


    //index[0] must be "partial match" 
    private static final String[] optionsForMatch = {"Partial match           ", "Exact match        "};

    //index[0] must be "larger than"
    private static final String[] optionsForIntValue = {"Value larger than      ", "Value less than      "};

    
    private List<Client> lista_clienti = new ArrayList<Client>();
    private List<Comanda> lista_comenzi = new ArrayList<Comanda>();
    private List<DetaliiComanda> lista_detalii_comenzi = new ArrayList<DetaliiComanda>();
    private List<Produs> lista_produse = new ArrayList<Produs>();
    
    private ClientTableImpl tableClient;
    private ComandaTableImpl tableComanda;
    private DetaliiComandaTableImpl tableDetaliiComanda;
    private ProdusTableImpl tableProdus;
    
    private PrelucrareDateClienti prelucrareDateClienti;
    private PrelucrareDateComenzi prelucrareDateComenzi;
    private PrelucrareDateDetaliiComenzi prelucrareDateDetaliiComenzi;
    private PrelucrareDateProduse prelucrareDateProduse;
    
	private JPanel panelClient = new JPanel(new BorderLayout());
	private JPanel panelComanda = new JPanel(new BorderLayout());
	private JPanel panelDetaliiComanda = new JPanel(new BorderLayout());
	private JPanel panelProdus = new JPanel(new BorderLayout());
	private JPanel panelIndex = new JPanel();
	private JPanel ContinutPanel = new JPanel(new BorderLayout());
	private CardLayout cardLayout = new CardLayout();
	
	
    private JButton butonClient;
    private JButton butonComanda;
    private JButton butonDetalii_Comanda;
    private JButton butonProdus;
    
	
    private JButton addButtonClient;
    private JButton searchButtonClient;
    private JButton deleteButtonClient;
    private JButton backButtonClient;
    
    private JButton addButtonComanda;
    private JButton searchButtonComanda;
    private JButton deleteButtonComanda;
    private JButton backButtonComanda;
    
    private JButton addButtonDetaliiComanda;
    private JButton searchButtonDetaliiComanda;
    private JButton deleteButtonDetaliiComanda;
    private JButton backButtonDetaliiComanda;
    
    private JButton addButtonProdus;
    private JButton searchButtonProdus;
    private JButton deleteButtonProdus;
    private JButton backButtonProdus;
    
    private JButton button_execute_client;
    private JButton button_execute_comanda;
    private JButton button_execute_d_comanda;
    private JButton button_execute_produs;

    private JComboBox<String> matchSearchOptionsClient;
    private JComboBox<String> matchSearchOptionsComanda;
    private JComboBox<String> matchSearchOptionsDetaliiComanda;
    private JComboBox<String> matchSearchOptionsProdus;
    private JComboBox<String> valueSearchOptionsComanda;
    private JComboBox<String> valueSearchOptionsDetaliiComanda;
    private JComboBox<String> valueSearchOptionsProdus;

    private JTextField CNPInput;
    private JTextField NumeInput;
    private JTextField PrenumeInput;
    private JTextField AdresaInput;
    private JTextField Nr_telefonInput;
    
    private JTextField IdComandaInput;
    private JTextField NrComandaInput;
    private JTextField CNPClientComandaInput;
    private JTextField DataComandaInput;
    
    private JTextField IdDetaliiComandaInput;
    private JTextField IdComandaDetaliiComandaInput;
    private JTextField CodProdusInput;
    private JTextField NrBucComandateInput;
    
    
    private JTextField CodProdusPInput;
    private JTextField NumeProdusInput;
    private JTextField NrBucStocInput;
    private JTextField PretInputInput;
    
    private JTextField InterogareComplexaInputClient;
    private JTextField InterogareComplexaInputComanda;
    private JTextField InterogareComplexaInputDComanda;
    private JTextField InterogareComplexaInputProdus;
    

    public DatabaseViewer() {
        super("Evidenta produselor dintr-un Hypermarket");

        prelucrareDateClienti = new PrelucrareDateClienti(lista_clienti);
        prelucrareDateClienti.readAllClientTable();      
        prelucrareDateComenzi = new PrelucrareDateComenzi(lista_comenzi);
        prelucrareDateComenzi.readAllComandaTable();     
        prelucrareDateDetaliiComenzi = new PrelucrareDateDetaliiComenzi(lista_detalii_comenzi);
        prelucrareDateDetaliiComenzi.readAllDetaliiComandaTable();      
        prelucrareDateProduse = new PrelucrareDateProduse(lista_produse);
        prelucrareDateProduse.readAllProdusTable();        
        
        makeControlPanelIndex();
       
        panelClient.setBorder(BorderFactory.createTitledBorder("  TABELA CLIENT "));
        panelClient.setBackground(Color.GREEN);
        panelClient.add(makeInputFieldsPANEL_CLIENT(), BorderLayout.NORTH);
        tableClient = new ClientTableImpl(lista_clienti);
        panelClient.add(makeResultTable(tableClient), BorderLayout.CENTER);
        panelClient.add(InterogareComplexaClientField(), BorderLayout.SOUTH);
        
        panelComanda.setBorder(BorderFactory.createTitledBorder("  TABELA COMANDA ")); 
        panelComanda.setBackground(Color.GREEN);
        panelComanda.add(makeInputFieldsPANEL_COMANDA(), BorderLayout.NORTH);
        tableComanda = new ComandaTableImpl(lista_comenzi);
        panelComanda.add(makeResultTable(tableComanda), BorderLayout.CENTER);
        panelComanda.add(InterogareComplexaComandaField(), BorderLayout.SOUTH);
        
        panelDetaliiComanda.setBorder(BorderFactory.createTitledBorder("  TABELA DETALII COMANDA ")); 
        panelDetaliiComanda.setBackground(Color.GREEN);
        panelDetaliiComanda.add(makeInputFieldsPANEL_DETALII_COMANDA(), BorderLayout.NORTH);
        tableDetaliiComanda = new DetaliiComandaTableImpl(lista_detalii_comenzi);
        panelDetaliiComanda.add(makeResultTable(tableDetaliiComanda), BorderLayout.CENTER);
        panelDetaliiComanda.add(InterogareComplexaDComandaField(), BorderLayout.SOUTH);
       
        panelProdus.setBorder(BorderFactory.createTitledBorder("  TABELA PRODUS ")); 
        panelProdus.setBackground(Color.GREEN);
        panelProdus.add(makeInputFieldsPANEL_PRODUS(), BorderLayout.NORTH);
        tableProdus = new ProdusTableImpl(lista_produse);
        panelProdus.add(makeResultTable(tableProdus), BorderLayout.CENTER);
        panelProdus.add(InterogareComplexaProdusField(), BorderLayout.SOUTH);
                
        ContinutPanel.setLayout(cardLayout);
        
        ContinutPanel.add(panelClient, PANEL_CLIENT);
        ContinutPanel.add(panelComanda, PANEL_COMANDA);
        ContinutPanel.add(panelDetaliiComanda, PANEL_DETALII_COMANDA);
        ContinutPanel.add(panelProdus, PANEL_PRODUS);
        ContinutPanel.add(panelIndex, PANEL_INDEX);
        
        this.setContentPane(ContinutPanel);		
		cardLayout.show(ContinutPanel, PANEL_INDEX);
        
		addListnersClient();
		addListnersComanda();
		addListnersDetaliiComanda();
		addListnersProdus();

    }
    
    private JPanel InterogareComplexaClientField() {
    	
    	JPanel InterogareComplexaPanel = new JPanel();
        button_execute_client = new JButton("Execute");
    	InterogareComplexaInputClient = new JTextField(120);
    	JLabel eticheta = new JLabel("Interogare Complexa :");
    	
    	eticheta.setBackground(Color.ORANGE);
    	eticheta.setOpaque(true);
    	eticheta.setForeground(Color.BLACK);
    	
    	button_execute_client.setBackground(Color.ORANGE);
    	button_execute_client.setContentAreaFilled(false);
    	button_execute_client.setOpaque(true);
    	button_execute_client.setForeground(Color.BLACK);
	
    	InterogareComplexaPanel.add(eticheta);
    	InterogareComplexaPanel.add(InterogareComplexaInputClient, BorderLayout.SOUTH);
    	InterogareComplexaPanel.add(button_execute_client);
    	
    	return InterogareComplexaPanel;
    	
    }
    
    
    private JPanel InterogareComplexaComandaField() {
    	
    	JPanel InterogareComplexaPanel = new JPanel();
    	button_execute_comanda = new JButton("Execute");
    	InterogareComplexaInputComanda = new JTextField(120);
    	JLabel eticheta = new JLabel("Interogare Complexa :");
    	
    	eticheta.setBackground(Color.ORANGE);
    	eticheta.setOpaque(true);
    	eticheta.setForeground(Color.BLACK);
    	
    	button_execute_comanda.setBackground(Color.ORANGE);
    	button_execute_comanda.setContentAreaFilled(false);
    	button_execute_comanda.setOpaque(true);
    	button_execute_comanda.setForeground(Color.BLACK);
	
    	InterogareComplexaPanel.add(eticheta);
    	InterogareComplexaPanel.add(InterogareComplexaInputComanda, BorderLayout.SOUTH);
    	InterogareComplexaPanel.add(button_execute_comanda);
    	
    	return InterogareComplexaPanel;
    	
    }
    
    
    private JPanel InterogareComplexaDComandaField() {
    	
    	JPanel InterogareComplexaPanel = new JPanel();
    	button_execute_d_comanda = new JButton("Execute");
    	InterogareComplexaInputDComanda = new JTextField(120);
    	JLabel eticheta = new JLabel("Interogare Complexa :");
    	
    	eticheta.setBackground(Color.ORANGE);
    	eticheta.setOpaque(true);
    	eticheta.setForeground(Color.BLACK);
    	
    	button_execute_d_comanda.setBackground(Color.ORANGE);
    	button_execute_d_comanda.setContentAreaFilled(false);
    	button_execute_d_comanda.setOpaque(true);
    	button_execute_d_comanda.setForeground(Color.BLACK);
	
    	InterogareComplexaPanel.add(eticheta);
    	InterogareComplexaPanel.add(InterogareComplexaInputDComanda, BorderLayout.SOUTH);
    	InterogareComplexaPanel.add(button_execute_d_comanda);
    	
    	return InterogareComplexaPanel;
    	
    }

    private JPanel InterogareComplexaProdusField() {
    	
    	JPanel InterogareComplexaPanel = new JPanel();
    	button_execute_produs = new JButton("Execute");
    	InterogareComplexaInputProdus = new JTextField(120);
    	JLabel eticheta = new JLabel("Interogare Complexa :");
    	
    	eticheta.setBackground(Color.ORANGE);
    	eticheta.setOpaque(true);
    	eticheta.setForeground(Color.BLACK);
    	
    	button_execute_produs.setBackground(Color.ORANGE);
    	button_execute_produs.setContentAreaFilled(false);
    	button_execute_produs.setOpaque(true);
    	button_execute_produs.setForeground(Color.BLACK);
	
    	InterogareComplexaPanel.add(eticheta);
    	InterogareComplexaPanel.add(InterogareComplexaInputProdus, BorderLayout.SOUTH);
    	InterogareComplexaPanel.add(button_execute_produs);
    	
    	return InterogareComplexaPanel;
    	
    }
    
    
    private JPanel makeInputFieldsPANEL_CLIENT() {
        JPanel inputFieldsPanel = new JPanel();
        inputFieldsPanel.setLayout(new FlowLayout());

        JLabel CNP = new JLabel(InfoDatabase.CNP_CLIENT + ":");
        inputFieldsPanel.add(CNP);
        CNPInput = new JTextField(13);
        inputFieldsPanel.add(CNPInput);

        JLabel Nume = new JLabel(InfoDatabase.NUME_CLIENT + ":");
        inputFieldsPanel.add(Nume);
        NumeInput = new JTextField(10);
        inputFieldsPanel.add(NumeInput);
        
        JLabel Prenume = new JLabel(InfoDatabase.PRENUME_CLIENT + ":");
        inputFieldsPanel.add(Prenume);
        PrenumeInput = new JTextField(15);
        inputFieldsPanel.add(PrenumeInput);

        JLabel Adresa = new JLabel(InfoDatabase.ADRESA_CLIENT + ":");
        inputFieldsPanel.add(Adresa);
        AdresaInput = new JTextField(15);
        inputFieldsPanel.add(AdresaInput);
        
        JLabel Nr_telefon = new JLabel(InfoDatabase.NR_TELEFON_CLIENT + ":");
        inputFieldsPanel.add(Nr_telefon);
        Nr_telefonInput = new JTextField(10);
        inputFieldsPanel.add(Nr_telefonInput);
      
        inputFieldsPanel.add(makeControlPanelClient());

        return inputFieldsPanel;
    }
    
    private JPanel makeInputFieldsPANEL_COMANDA() {
        JPanel inputFieldsPanel = new JPanel();
        inputFieldsPanel.setLayout(new FlowLayout());

        JLabel id_comanda = new JLabel(InfoDatabase.ID_COMANDA + ":");
        inputFieldsPanel.add(id_comanda);
        IdComandaInput = new JTextField(7);
        inputFieldsPanel.add(IdComandaInput);

        JLabel nr_comanda = new JLabel(InfoDatabase.NR_COMANDA + ":");
        inputFieldsPanel.add(nr_comanda);
        NrComandaInput = new JTextField(10);
        inputFieldsPanel.add(NrComandaInput);
        
        JLabel cnp_client = new JLabel(InfoDatabase.CNP_CLIENT_COMANDA + ":");
        inputFieldsPanel.add(cnp_client);
        CNPClientComandaInput = new JTextField(15);
        inputFieldsPanel.add(CNPClientComandaInput);

        JLabel data_comanda = new JLabel(InfoDatabase.DATA_COMANDA + ":");
        inputFieldsPanel.add(data_comanda);
        DataComandaInput = new JTextField(15);
        inputFieldsPanel.add(DataComandaInput);
         
        inputFieldsPanel.add(makeControlPanelComanda());


        return inputFieldsPanel;
    }
    

    private JPanel makeInputFieldsPANEL_DETALII_COMANDA() {
        JPanel inputFieldsPanel = new JPanel();
        inputFieldsPanel.setLayout(new FlowLayout());
        
        JLabel id_detalii_comanda = new JLabel(InfoDatabase.ID_DETALII_COMANDA + ":");
        inputFieldsPanel.add(id_detalii_comanda);
        IdDetaliiComandaInput = new JTextField(13);
        inputFieldsPanel.add(IdDetaliiComandaInput);

        JLabel id_comanda = new JLabel(InfoDatabase.ID_COMANDA_DETALII_COMANDA + ":");
        inputFieldsPanel.add(id_comanda);
        IdComandaDetaliiComandaInput = new JTextField(10);
        inputFieldsPanel.add(IdComandaDetaliiComandaInput);
        
        JLabel cod_produs = new JLabel(InfoDatabase.COD_PRODUS_DETALII_COMANDA + ":");
        inputFieldsPanel.add(cod_produs);
        CodProdusInput = new JTextField(15);
        inputFieldsPanel.add(CodProdusInput);

        JLabel nr_buc_comandate = new JLabel(InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA + ":");
        inputFieldsPanel.add(nr_buc_comandate);
        NrBucComandateInput = new JTextField(15);
        inputFieldsPanel.add(NrBucComandateInput);
        
        inputFieldsPanel.add(makeControlPanelDetaliiComanda());

        return inputFieldsPanel;
    }
      
    
    private JPanel makeInputFieldsPANEL_PRODUS() {
        JPanel inputFieldsPanel = new JPanel();
        inputFieldsPanel.setLayout(new FlowLayout());
        

        JLabel cod_produs = new JLabel(InfoDatabase.COD_PRODUS + ":");
        inputFieldsPanel.add(cod_produs);
        CodProdusPInput = new JTextField(13);
        inputFieldsPanel.add(CodProdusPInput);

        JLabel nume = new JLabel(InfoDatabase.NUME_PRODUS + ":");
        inputFieldsPanel.add(nume);
        NumeProdusInput = new JTextField(10);
        inputFieldsPanel.add(NumeProdusInput);
        
        JLabel nr_buc_stoc = new JLabel(InfoDatabase.NR_BUC_STOC + ":");
        inputFieldsPanel.add(nr_buc_stoc);
        NrBucStocInput = new JTextField(15);
        inputFieldsPanel.add(NrBucStocInput);

        JLabel pret = new JLabel(InfoDatabase.PRET + ":");
        inputFieldsPanel.add(pret);
        PretInputInput = new JTextField(15);
        inputFieldsPanel.add(PretInputInput);
  
        inputFieldsPanel.add(makeControlPanelProdus());

        return inputFieldsPanel;
    }
    
    
    private void makeControlPanelIndex() {
    	
    	panelIndex.setLayout(null);
    	
        butonClient = new JButton("Client");
        butonComanda = new JButton("Comanda");
        butonDetalii_Comanda = new JButton("Detalii");
        butonProdus = new JButton("Produs");
        
        panelIndex = new ImagePanel(new ImageIcon("src\\interfata/background.png").getImage());
        
        butonClient.setBounds(410,230,200,50);
        butonClient.setBackground(Color.YELLOW);
        butonClient.setContentAreaFilled(false);
        butonClient.setOpaque(true);
        butonClient.setForeground(Color.BLACK);
        
        butonComanda.setBounds(660,230,200,50);
        butonComanda.setBackground(Color.YELLOW);
        butonComanda.setContentAreaFilled(false);
        butonComanda.setOpaque(true);
        butonComanda.setForeground(Color.BLACK);
        
        butonDetalii_Comanda.setBounds(410,290,200,50);
        butonDetalii_Comanda.setBackground(Color.YELLOW);
        butonDetalii_Comanda.setContentAreaFilled(false);
        butonDetalii_Comanda.setOpaque(true);
        butonDetalii_Comanda.setForeground(Color.BLACK);
        
        butonProdus.setBounds(660,290,200,50);
        butonProdus.setBackground(Color.YELLOW);
        butonProdus.setContentAreaFilled(false);
        butonProdus.setOpaque(true);
        butonProdus.setForeground(Color.BLACK);
        
        panelIndex.add(butonClient);
        panelIndex.add(butonComanda);
        panelIndex.add(butonDetalii_Comanda);
        panelIndex.add(butonProdus);
 
        actionListener a1 = new actionListener();
        
        butonClient.addActionListener(a1);
        butonComanda.addActionListener(a1);
        butonDetalii_Comanda.addActionListener(a1);
        butonProdus.addActionListener(a1);
  
    }
    
    
    private JPanel makeControlPanelClient() {
        JPanel contolPanel = new JPanel();
        contolPanel.setLayout(new BoxLayout(contolPanel, BoxLayout.X_AXIS));

        addButtonClient = new JButton("Add");
        addButtonClient.setBackground(Color.GREEN);
        addButtonClient.setContentAreaFilled(false);
        addButtonClient.setOpaque(true);
        addButtonClient.setForeground(Color.BLACK);
        contolPanel.add(addButtonClient);

        searchButtonClient = new JButton("Search");
        searchButtonClient.setBackground(Color.YELLOW);
        searchButtonClient.setContentAreaFilled(false);
        searchButtonClient.setOpaque(true);
        searchButtonClient.setForeground(Color.BLACK);
        contolPanel.add(searchButtonClient);
        
        deleteButtonClient = new JButton("Delete");
        deleteButtonClient.setBackground(Color.RED);
        deleteButtonClient.setContentAreaFilled(false);
        deleteButtonClient.setOpaque(true);
        deleteButtonClient.setForeground(Color.WHITE);
        contolPanel.add(deleteButtonClient);
        
        backButtonClient = new JButton("Back");
        backButtonClient.setForeground(Color.RED);
        contolPanel.add(backButtonClient);
        
        backButtonClient.addActionListener(new actionListener());

        contolPanel.add(makeSearchOptionBoxClient());

        return contolPanel;
    }
    
    private JPanel makeControlPanelComanda() {
        JPanel contolPanel = new JPanel();
        contolPanel.setLayout(new BoxLayout(contolPanel, BoxLayout.X_AXIS));

        addButtonComanda = new JButton("Add");
        addButtonComanda.setBackground(Color.GREEN);
        addButtonComanda.setContentAreaFilled(false);
        addButtonComanda.setOpaque(true);
        addButtonComanda.setForeground(Color.BLACK);
        contolPanel.add(addButtonComanda);

        searchButtonComanda = new JButton("Search");
        searchButtonComanda.setBackground(Color.YELLOW);
        searchButtonComanda.setContentAreaFilled(false);
        searchButtonComanda.setOpaque(true);
        searchButtonComanda.setForeground(Color.BLACK);
        contolPanel.add(searchButtonComanda);
        
        deleteButtonComanda = new JButton("Delete");
        deleteButtonComanda.setBackground(Color.RED);
        deleteButtonComanda.setContentAreaFilled(false);
        deleteButtonComanda.setOpaque(true);
        deleteButtonComanda.setForeground(Color.WHITE);
        contolPanel.add(deleteButtonComanda);
        
        backButtonComanda = new JButton("Back");
        backButtonComanda.setForeground(Color.RED);
        contolPanel.add(backButtonComanda);
        
        backButtonComanda.addActionListener(new actionListener());

        contolPanel.add(makeSearchOptionBoxComanda());

        return contolPanel;
    }
    
    private JPanel makeControlPanelDetaliiComanda() {
        JPanel contolPanel = new JPanel();
        contolPanel.setLayout(new BoxLayout(contolPanel, BoxLayout.X_AXIS));

        addButtonDetaliiComanda = new JButton("Add");
        addButtonDetaliiComanda.setBackground(Color.GREEN);
        addButtonDetaliiComanda.setContentAreaFilled(false);
        addButtonDetaliiComanda.setOpaque(true);
        addButtonDetaliiComanda.setForeground(Color.BLACK);
        contolPanel.add(addButtonDetaliiComanda);

        searchButtonDetaliiComanda = new JButton("Search");
        searchButtonDetaliiComanda.setBackground(Color.YELLOW);
        searchButtonDetaliiComanda.setContentAreaFilled(false);
        searchButtonDetaliiComanda.setOpaque(true);
        searchButtonDetaliiComanda.setForeground(Color.BLACK);
        contolPanel.add(searchButtonDetaliiComanda);
        
        deleteButtonDetaliiComanda = new JButton("Delete");
        deleteButtonDetaliiComanda.setBackground(Color.RED);
        deleteButtonDetaliiComanda.setContentAreaFilled(false);
        deleteButtonDetaliiComanda.setOpaque(true);
        deleteButtonDetaliiComanda.setForeground(Color.WHITE);
        contolPanel.add(deleteButtonDetaliiComanda);
        
        backButtonDetaliiComanda = new JButton("Back");
        backButtonDetaliiComanda.setForeground(Color.RED);
        contolPanel.add(backButtonDetaliiComanda);
        
        backButtonDetaliiComanda.addActionListener(new actionListener());

        contolPanel.add(makeSearchOptionBoxDetaliiComanda());

        return contolPanel;
    }
    
    private JPanel makeControlPanelProdus() {
        JPanel contolPanel = new JPanel();
        contolPanel.setLayout(new BoxLayout(contolPanel, BoxLayout.X_AXIS));

        addButtonProdus = new JButton("Add");
        addButtonProdus.setBackground(Color.GREEN);
        addButtonProdus.setContentAreaFilled(false);
        addButtonProdus.setOpaque(true);
        addButtonProdus.setForeground(Color.BLACK);
        contolPanel.add(addButtonProdus);

        searchButtonProdus = new JButton("Search");
        searchButtonProdus.setBackground(Color.YELLOW);
        searchButtonProdus.setContentAreaFilled(false);
        searchButtonProdus.setOpaque(true);
        searchButtonProdus.setForeground(Color.BLACK);
        contolPanel.add(searchButtonProdus);
        
        deleteButtonProdus = new JButton("Delete");
        deleteButtonProdus.setBackground(Color.RED);
        deleteButtonProdus.setContentAreaFilled(false);
        deleteButtonProdus.setOpaque(true);
        deleteButtonProdus.setForeground(Color.WHITE);
        contolPanel.add(deleteButtonProdus);
        
        backButtonProdus = new JButton("Back");
        backButtonProdus.setForeground(Color.RED);
        contolPanel.add(backButtonProdus);
        
        backButtonProdus.addActionListener(new actionListener());

        contolPanel.add(makeSearchOptionBoxProdus());

        return contolPanel;
    }
    
    private JPanel makeSearchOptionBoxClient() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        optionsPanel.setBorder(new TitledBorder("Search options"));

        matchSearchOptionsClient = new JComboBox<String>(optionsForMatch);
        matchSearchOptionsClient.setSelectedIndex(0);
        optionsPanel.add(matchSearchOptionsClient);
        
        return optionsPanel;        
    }
    
    private JPanel makeSearchOptionBoxComanda() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        optionsPanel.setBorder(new TitledBorder("Search options"));

        matchSearchOptionsComanda = new JComboBox<String>(optionsForMatch);
        matchSearchOptionsComanda.setSelectedIndex(0);
        optionsPanel.add(matchSearchOptionsComanda);
        
        valueSearchOptionsComanda = new JComboBox<String>(optionsForIntValue);
        valueSearchOptionsComanda.setSelectedIndex(0);
        optionsPanel.add(valueSearchOptionsComanda);

        return optionsPanel;        
    }
    
    private JPanel makeSearchOptionBoxDetaliiComanda() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        optionsPanel.setBorder(new TitledBorder("Search options"));

        matchSearchOptionsDetaliiComanda = new JComboBox<String>(optionsForMatch);
        matchSearchOptionsDetaliiComanda.setSelectedIndex(0);
        optionsPanel.add(matchSearchOptionsDetaliiComanda);
        
        valueSearchOptionsDetaliiComanda = new JComboBox<String>(optionsForIntValue);
        valueSearchOptionsDetaliiComanda.setSelectedIndex(0);
        optionsPanel.add(valueSearchOptionsDetaliiComanda);

        return optionsPanel;        
    }
    
    private JPanel makeSearchOptionBoxProdus() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        optionsPanel.setBorder(new TitledBorder("Search options"));

        matchSearchOptionsProdus = new JComboBox<String>(optionsForMatch);
        matchSearchOptionsProdus.setSelectedIndex(0);
        optionsPanel.add(matchSearchOptionsProdus);
        
        valueSearchOptionsProdus = new JComboBox<String>(optionsForIntValue);
        valueSearchOptionsProdus.setSelectedIndex(0);
        optionsPanel.add(valueSearchOptionsProdus);

        return optionsPanel;        
    }

    private JScrollPane makeResultTable(AbstractTableModel model) {
        JTable resultTable = new JTable(model);
        resultTable.setOpaque(false);
        resultTable.setBackground(new Color(179, 248,  255));
        resultTable.getTableHeader().setOpaque(false);
        resultTable.getTableHeader().setBackground(new Color(69, 57,  238));
        resultTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        return scrollPane;
    }
    

    private void addListnersClient() {
    	
        addButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateClienti.addClient(createClient());
                    tableClient.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
            

        searchButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateClienti.searchInClientTable(CNPInput.getText(),
                    		NumeInput.getText(),
                    		PrenumeInput.getText(),
                    		AdresaInput.getText(),
                    		Nr_telefonInput.getText(),
                            (matchSearchOptionsClient.getSelectedIndex() == 0));
                    tableClient.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });        
        
        deleteButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateClienti.deleteFromClientTable(CNPInput.getText(),
                    		NumeInput.getText(),
                    		PrenumeInput.getText(),
                    		AdresaInput.getText(),
                    		Nr_telefonInput.getText(),
                            (matchSearchOptionsClient.getSelectedIndex() == 0));
                    tableClient.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        }); 
        
    	button_execute_client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateClienti.InterogareComplexaClient(InterogareComplexaInputClient.getText());
                    tableClient.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });

    }
    
    private void addListnersComanda() {
    	
	      addButtonComanda.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent arg0) {
	          try {
	          	prelucrareDateComenzi.addComanda(createComanda());
	          	tableComanda.fireTableDataChanged();
	          } catch (Exception e) {
	              System.out.println(e);
	              e.printStackTrace();
	          }
	      }
	  });
	      
        searchButtonComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateComenzi.searchInComandaTable(parseIdComandaInput(), parseNrComandaInput(), 
                			CNPClientComandaInput.getText(), DataComandaInput.getText(),
                			(matchSearchOptionsComanda.getSelectedIndex() == 0), (valueSearchOptionsComanda.getSelectedIndex() == 0));
                	tableComanda.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });	     
        
        deleteButtonComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateComenzi.deleteFromComandaTable(parseIdComandaInput(), parseNrComandaInput(), 
                			CNPClientComandaInput.getText(), DataComandaInput.getText(),
                			(matchSearchOptionsComanda.getSelectedIndex() == 0), (valueSearchOptionsComanda.getSelectedIndex() == 0));
                	tableComanda.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });	
        
    	button_execute_comanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateComenzi.InterogareComplexaComanda(InterogareComplexaInputComanda.getText());
                	tableComanda.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
	
    }

    
    private void addListnersDetaliiComanda() {
    	
        
        addButtonDetaliiComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateDetaliiComenzi.addDetaliiComanda(createDetaliiComanda());
                	tableDetaliiComanda.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
        
        
        searchButtonDetaliiComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateDetaliiComenzi.searchInDetaliiComandaTable(parseIdDetaliiComandaInput(), parseIdComandaDetaliiComandaInput(), 
                			CodProdusInput.getText(), parseNrBucComandateInput(), (matchSearchOptionsDetaliiComanda.getSelectedIndex() == 0), 
                			(valueSearchOptionsDetaliiComanda.getSelectedIndex() == 0));
                	tableDetaliiComanda.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
        
        deleteButtonDetaliiComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateDetaliiComenzi.deleteFromDetaliiComandaTable(parseIdDetaliiComandaInput(), parseIdComandaDetaliiComandaInput(), 
                			CodProdusInput.getText(), parseNrBucComandateInput(), (matchSearchOptionsDetaliiComanda.getSelectedIndex() == 0), 
                			(valueSearchOptionsDetaliiComanda.getSelectedIndex() == 0));
                	tableDetaliiComanda.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
        
    	button_execute_d_comanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateDetaliiComenzi.InterogareComplexaDetaliiComanda(InterogareComplexaInputDComanda.getText());
                	tableDetaliiComanda.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
	
    }
    
    private void addListnersProdus() {
    	
        addButtonProdus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateProduse.addProdus(createProdus());
                	tableProdus.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
        
        searchButtonProdus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateProduse.searchInProdusTable(CodProdusPInput.getText(), NumeProdusInput.getText(), 
                			parseNrBucStocInput(), parsePretInputInput(), (matchSearchOptionsProdus.getSelectedIndex() == 0), 
                			(valueSearchOptionsProdus.getSelectedIndex() == 0));
                	tableProdus.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
        
        deleteButtonProdus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateProduse.deleteFromProdusTable(CodProdusPInput.getText(), NumeProdusInput.getText(), 
                			parseNrBucStocInput(), parsePretInputInput(), (matchSearchOptionsProdus.getSelectedIndex() == 0), 
                			(valueSearchOptionsProdus.getSelectedIndex() == 0));
                	tableProdus.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
        
    	button_execute_produs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                	prelucrareDateProduse.InterogareComplexaProdus(InterogareComplexaInputProdus.getText());
                	tableProdus.fireTableDataChanged();
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });

    }
    private Client createClient() {
    	
        String CNP = CNPInput.getText();
        String Nume = NumeInput.getText();
        String Prenume = PrenumeInput.getText();
        String Adresa = AdresaInput.getText();
        String Nr_telefon = Nr_telefonInput.getText();

        if (CNP.length() != 0 && Nume.length() != 0 && Prenume.length() != 0 && Adresa.length() != 0 && Nr_telefon.length() != 0 ) { 
            return new Client(CNP, Nume, Prenume, Adresa, Nr_telefon);
        } else {
            return null;
        }
    }
    
    private Comanda createComanda() {
    	
    	int id_comanda = parseIdComandaInput();
    	int nr_comanda = parseNrComandaInput();
    	String cnp_client = CNPClientComandaInput.getText();
    	String data_comanda = DataComandaInput.getText();

        if (id_comanda != 0 && nr_comanda != 0 && cnp_client.length() != 0 && data_comanda.length() != 0 ) {
            return new Comanda(id_comanda, nr_comanda, cnp_client, data_comanda);
        } else {
            return null;
        }
    }
    
    private int parseIdComandaInput() {
        String input = IdComandaInput.getText();
        if(input.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }
    
    private int parseNrComandaInput() {
        String input = NrComandaInput.getText();
        if(input.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }
    

    private DetaliiComanda createDetaliiComanda() {
    	
        int IdDetaliiComanda = parseIdDetaliiComandaInput();
        int IdComandaDetaliiComanda = parseIdComandaDetaliiComandaInput();
        String CodProdus = CodProdusInput.getText();
        int NrBucComandate = parseNrBucComandateInput();

        if (IdDetaliiComanda != 0 && IdComandaDetaliiComanda != 0 && CodProdus.length() != 0 && NrBucComandate != 0) {
            return new DetaliiComanda(IdDetaliiComanda, IdComandaDetaliiComanda, CodProdus, NrBucComandate);
        } else {
            return null;
        }
    }
    
    private int parseIdDetaliiComandaInput() {
        String input = IdDetaliiComandaInput.getText();
        if(input.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }
    
    private int parseIdComandaDetaliiComandaInput() {
        String input = IdComandaDetaliiComandaInput.getText();
        if(input.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }
    
    private int parseNrBucComandateInput() {
        String input = NrBucComandateInput.getText();
        if(input.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }
    
    
    private Produs createProdus() {
    	
        String CodProdusP = CodProdusPInput.getText();
        String  NumeProdus = NumeProdusInput.getText();
        int NrBucStoc = parseNrBucStocInput();
        double PretInput = parsePretInputInput();

        if (CodProdusP.length() != 0 && NumeProdus.length() != 0 && NrBucStoc != 0 && PretInput != 0) {
            return new Produs(CodProdusP, NumeProdus, NrBucStoc, PretInput);
        } else {
            return null;
        }
    }
    
    private int parseNrBucStocInput() {
        String input = NrBucStocInput.getText();
        if(input.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }
    
    private double parsePretInputInput() {
        String input = PretInputInput.getText();
        if(input.length() == 0) {
            return 0;
        } else {
            return Double.parseDouble(input);
        }
    }
    
    
	public class actionListener implements ActionListener{
		
		public void actionPerformed (ActionEvent event) {
			JButton src = (JButton) event.getSource();
			
			if(src.equals(butonClient)) {
				cardLayout.show(ContinutPanel, PANEL_CLIENT);
			}
			
			if(src.equals(butonComanda)) {
				cardLayout.show(ContinutPanel, PANEL_COMANDA);
			}
			
			if(src.equals(butonDetalii_Comanda)) {
				cardLayout.show(ContinutPanel, PANEL_DETALII_COMANDA);
			}
			
			if(src.equals(butonProdus)) {
				cardLayout.show(ContinutPanel, PANEL_PRODUS);
			}
			
			if(src.equals(backButtonClient)) {
				cardLayout.show(ContinutPanel, PANEL_INDEX);
			}
			
			if(src.equals(backButtonComanda)) {
				cardLayout.show(ContinutPanel, PANEL_INDEX);
			}
			
			if(src.equals(backButtonDetaliiComanda)) {
				cardLayout.show(ContinutPanel, PANEL_INDEX);
			}
			
			if(src.equals(backButtonProdus)) {
				cardLayout.show(ContinutPanel, PANEL_INDEX);
			}
			
		}

	}
 
}