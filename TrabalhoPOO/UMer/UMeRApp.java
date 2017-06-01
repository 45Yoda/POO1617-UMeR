import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.TreeSet;
import java.util.Set;
import java.lang.Object;
import java.io.Serializable;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class UMeRApp implements Serializable
{
    private UMeRApp() {}
    private static UMeR umer;
    private static Menu mMain, mCliente, mSolicitar, mRegistos, mMotorista, mMotoristaEmp, mVeiculo;
        
    public static void main(String [] args){
        carregaMenus();
        carregaDados();
        carregaMenuP();
    
        try{
            umer.gravar();
        }
        catch(IOException e){
            System.out.println("Não gravei os dados");
        }
        
        System.out.println("Até breve!");
    }
       
     public static void carregaMenus(){
        String [] principal = {"Iniciar Sessão",
                               "Registar Utilizador",
                               "Lista de Empresas",
                               "Top 10 Clientes",
                               "Top 5 Motoristas"};
                              
        String [] cliente ={"Avaliar Motorista",
                            "Consultar Historico",
                            "Solicitar Viagem",
                            "Terminar Sessão"};
 
        String[] solicitar = {"Solicitar taxi mais próximo",
                            "Solicitar taxi especifico"};
        
        String [] motorista = {"Adicionar Veiculo",
                              "Associar Veiculo",
                              "Associar Empresa",
                              "Consultar Historico",
                              "Registar Viagem",
                              "Sinalizar Disponibilidade",
                               "Terminar Sessão"};
                               
        String [] motoristaEmp ={"Adicionar Veiculo",
                                 "Associar Veiculo",
                                 "Consultar Historico",
                                 "Lista de Motoristas da Empresa",
                                 "Lista de Viaturas duma Empresa",
                                 "Registar Viagem",
                                 "Sinalizar Disponibilidade",
                                 "Desassociar Empresa",
                                 "Terminar Sessão"};
                               
        String [] registar = {"Cliente",
                              "Motorista"};
                              
        String [] veiculo = {"Carrinha",
                            "Carro",
                            "Mota"};
          
        mMain = new Menu(principal);
        mCliente = new Menu(cliente);
        mSolicitar = new Menu(solicitar);
        mRegistos = new Menu(registar);
        mMotorista = new Menu(motorista);
        mMotoristaEmp = new Menu(motoristaEmp);
        mVeiculo = new Menu(veiculo);
    }
    
    private static void carregaDados(){
        try{
            umer = UMeR.initApp();
        }
        catch(IOException e){
            umer = new UMeR();
            System.out.println("Não consegui ler os dados!\nErro de leitura.");
        }
        catch(ClassNotFoundException e){
            umer = new UMeR();
            System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch(ClassCastException e){
            umer = new UMeR();
            System.out.println("Não consegui ler os dados!\nErro de formato.");        
        }
    
    }
    
    private static void carregaMenuP(){
        do{
           mMain.executa();
            
           switch(mMain.getOpcao()){
                case 1: iniciaSessao(); 
                        break;
                case 2: registaUtilizador(); 
                        break;
                case 3: listaEmpresas(); 
                        break;
                case 4: top10Clientes(); 
                        break;
               /* case 5: top5Motoristas(); 
                        break;*/
           } 
        }while(mMain.getOpcao() != 0); 
    }
    
    private static void carregaMenuC(){
        do{
            mCliente.executa();
            
            switch(mCliente.getOpcao()){
                case 1: avaliaMotorista();
                        break;
                case 2: consultaHistorico();
                        break;
                case 3: carregaMenuSolicita();
                        break;
                case 4: umer.fechaSessao();
            }
        }while(mCliente.getOpcao() != 0);
    }
    
    private static void carregaMenuM(){
        do{
            mMotorista.executa();
            
            switch(mMotoristaEmp.getOpcao()){
             //   case 1: adicionaVeiculo();
                     //   break;
//
  //                      break;
             //   case 3: associaEmpresa();
                   //     break;
                case 4: consultaHistorico();
                        break;
               // case 5: registaViagem();
                   //     break;
                case 6: sinalizaDisp();
                        break;
                case 7: umer.fechaSessao();
            }
        }while(mMotorista.getOpcao() != 0);
    }

    private static void carregaMenuME(){
        do{
            mMotoristaEmp.executa();
            
            switch(mMotorista.getOpcao()){
               
                // case 1: adicionaVeiculo();
                //        break;
               // case 2: associaVeiculo();
                 //       break;
                case 3: consultaHistorico();
                        break;
                case 4: listaMotoristaEmp();
                        break;
                case 5: listaVeiculoEmp();
                        break;
              //  case 6: registaViagem();
             //           break;
                case 7: sinalizaDisp();
                        break;
               // case 8: desassociaEmpresa();
                 //       break;
             //   case 9: umer.fechaSessao();
            }
        }while(mMotoristaEmp.getOpcao() != 0);
    }
    
    private static void carregaMenuSolicita(){
        do{
            mSolicitar.executa();
            /*
            switch(mSolicitar.getOpcao()){
                case 1: solTaxiProx();
                        break;
                case 2: solTaxiEsp();
                        break;
            }*/
        }while(mSolicitar.getOpcao() != 0);
    }
    
    private static void carregaMenuVeivulo(){
        do {
            mVeiculo.executa();
            /*
            switch(mVeiculo.getOpcao()) {
                
                case 1: insereCarrinha();
                    break;
                case 2: insereCarro();
                    break;
                case 3: insereMota();
                    break;
            }*/
        }while(mVeiculo.getOpcao()!=0);
    }
    
    /*****************************************************menuP*****************************************************/  
    private static void registaUtilizador(){
        String email,nome,password,morada,data;
        Utilizador uti = null;
        Scanner input = new Scanner(System.in);
        mRegistos.executa();
        
        System.out.println("Insira o seu email: ");
        email = input.nextLine();
        
        System.out.println("Insira o seu nome: ");
        nome = input.nextLine();
        
        System.out.println("Insira a password: ");
        password = input.nextLine();
        
        System.out.println("Insira a morada: ");
        morada = input.nextLine();
        
        System.out.println("Insira a sua data de nascimento (dd-mm-yyyy)");
        data = input.nextLine();
        
        switch(mRegistos.getOpcao()){
            case 0: input.close();
                    System.out.println("Cancelou o registo");
                    return;
            case 1: uti = new Cliente();
                    break;
            case 2: uti = new Motorista();
                    break;
        }
        
        uti.setEmail(email);
        uti.setNome(nome);
        uti.setPass(password);
        uti.setMorada(morada);
        uti.setData(data);
        
        input.close();
        try{
            umer.registarUtilizador(uti);
        }
        catch(UtilizadorExistenteException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    private static void iniciaSessao(){
        Scanner scan = new Scanner(System.in);
        String mail, pass;
        
        System.out.println("Email: ");
        mail=scan.nextLine();
        System.out.println("Password: ");
        pass = scan.nextLine();
        
        try{
            umer.iniciaSessao(mail,pass);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        switch(umer.getTipoUtilizador()){
            case 1: carregaMenuC();
                    break;
            case 2: carregaMenuM();
                    break;
            case 3: carregaMenuME();
                    break;
        }
        System.out.println("Sessão iniciada com sucesso");
    }

    
    private static void listaEmpresas() {
        List<Empresa> emp = new ArrayList<Empresa>();
        emp = umer.getEmpresa().values().stream().collect(Collectors.toList());
        int i;
        StringBuilder sb = new StringBuilder();
        for(i=0;i<emp.size();i++){
            sb.append("Empresa: ").append(emp.get(i).toString()).append("\n").append("\n");
        }
        System.out.println(sb);
    }
    
    private static void top10Clientes() {
        Map<Double,Utilizador> clientes = new TreeMap<Double,Utilizador>();
        for(Utilizador u : umer.getUtilizadores().values())
            if(u instanceof Cliente) {
                double gasto = u.getQuantia();
                clientes.put(gasto,u);
            }
        List<Utilizador> ut = new ArrayList<Utilizador>();
        ut=clientes.values().stream().collect(Collectors.toList());
        int i;
        int c;
        StringBuilder sb = new StringBuilder();
        for(i=ut.size()-1,c=0;c<10;c++,i--)
            sb.append(ut.get(i).getNome()).append("\n");
    }
    
    private static void avaliaMotorista() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o email do motorista:\n");
        String email=scan.nextLine();
        System.out.println("Classifique o seu motorista (de 0 a 100): ");
        int c = scan.nextInt();
        Motorista m = (Motorista) umer.getUtilizadores().get(email);
        m.classMotorista(c);
    }
    
   private static void consultaHistorico() {
       Historico h = umer.getUser().getHistorico();
       StringBuilder sb = new StringBuilder();
       sb.append(h.toString()).append("\n");
    }
    
    private static void listaMotoristaEmp(){
        Scanner scan = new Scanner(System.in); 
        System.out.println("Digite o nome da empresa a que se pretende associar:\n");
        String nome=scan.nextLine();
        Motorista m = (Motorista) umer.getUser();
        umer.getEmpresa().get(nome).getMotoristas().add(m);
    }
    
    private static void listaVeiculoEmp() {
    Scanner scan = new Scanner(System.in); 
    System.out.println("Digite o nome da empresa a que pretende associar:\n");
    String nome=scan.nextLine();
    System.out.println("Indique a matricula do veiculo que pretende associar: \n");
    String mat = scan.nextLine();
    Veiculo v = umer.getVeiculo().get(mat);
    umer.getEmpresa().get(nome).getTaxis().add(v);
 
    }
    
    private static void sinalizaDisp() {
        Motorista m = (Motorista) umer.getMotorista().get(umer.getUser());
        Scanner scan = new Scanner(System.in); 
        System.out.println("Disponivel? (s/n)\n");
        String r = scan.nextLine();
        if (r.equals("s")) m.setDisp(true);
        else m.setDisp(false);
    }
}

