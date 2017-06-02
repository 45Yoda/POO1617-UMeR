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
    private static Menu mMain, mCliente, mSolicitar, mRegistos, mMotorista, mMotoristaEmp;
        
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
          
        mMain = new Menu(principal);
        mCliente = new Menu(cliente);
        mSolicitar = new Menu(solicitar);
        mRegistos = new Menu(registar);
        mMotorista = new Menu(motorista);
        mMotoristaEmp = new Menu(motoristaEmp);
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
            
            switch(mMotorista.getOpcao()){
                case 1: adicionaVeiculo();
                        break;
                case 2: try{associaVeiculo();
                            break;}
                        catch(VeiculoNaoExisteException e){
                            System.out.println(e);
                        }
                case 3: try{associaMotoristaEmp();
                            break;
                        }
                        catch(UtilizadorNaoExisteException | EmpresaNaoExisteException e){
                            System.out.println(e);
                        }
                            
                        
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
               
                case 1: adicionaVeiculo();
                        break;
                case 2: try{associaVeiculo();
                            break;}
                        catch(VeiculoNaoExisteException e){
                            System.out.println(e);
                        }
                case 3: try{associaVeiculoEmp();
                            break;}
                        catch(VeiculoNaoExisteException | EmpresaNaoExisteException e){
                            System.out.println(e);
                        }
                        
                case 4: consultaHistorico();
                        break;
                case 5: listaMotoristaEmp();
                        break;
                case 6: listaVeiculoEmp();
                        break;
              //  case 7: registaViagem();
             //           break;
                case 8: sinalizaDisp();
                        break;
                //case 9: desassociaEmpresa();
                 //       break;
                case 10: umer.fechaSessao();
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
        catch(UtilizadorExisteException e){
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
        if(emp.size()==0) System.out.println("Não existem empresas registadas!");
        else {
            for(i=0;i<emp.size();i++){
                System.out.println(emp.get(i).getNomeEmpresa());
            }
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
        ut = ut.subList(ut.size()-10,ut.size()-1);
        int i;
        for(i=ut.size()-1;i>=0;i--)
           System.out.println(ut.get(i).getNome()+"\n");
    }
    
    private static void avaliaMotorista() {
        //pode nao existir motorista
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
    

    private static void associaMotoristaEmp() throws UtilizadorNaoExisteException, EmpresaNaoExisteException{        

        Scanner scan = new Scanner(System.in); 
        System.out.println("Digite o nome da empresa a que se pretende associar:\n");
        String nome=scan.nextLine();
        Motorista m = (Motorista) umer.getUser();

        if(m == null)
            throw new UtilizadorNaoExisteException("Este Utilizador não existe");
       
        if(umer.getEmpresa().get(nome) == null)
            throw new EmpresaNaoExisteException("Esta Empresa não existe");
        
        umer.getEmpresa().get(nome).getMotoristas().add(m);
               
    }
    

    private static void associaVeiculoEmp() throws VeiculoNaoExisteException,EmpresaNaoExisteException {
        
        Scanner scan = new Scanner(System.in); 
        System.out.println("Digite o nome da empresa a que pretende associar:\n");
        String nome=scan.nextLine();
        System.out.println("Indique a matricula do veiculo que pretende associar: \n");
        String mat = scan.nextLine();
        
        //NEW!!!!!!!
        Veiculo v = umer.getVeiculo().get(mat);
        if(v == null){throw new VeiculoNaoExisteException("Veiculo não existe!");}
        
        if(umer.getEmpresa().get(nome) != null){
            umer.getEmpresa().get(nome).getTaxis().add(v);
        }
        else{
            throw new EmpresaNaoExisteException("Esta Empresa não existe!");
        }
        
    }
    
    private static void sinalizaDisp() {
        Motorista m = (Motorista) umer.getUser();
        Scanner scan = new Scanner(System.in); 
        System.out.println("Disponivel? (s/n)\n");
        String r = scan.nextLine();
        if (r.equals("s")) m.setDisp(true);
        else m.setDisp(false);
    }
    
    private static void adicionaVeiculo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Indique a matricula do veiculo a registar: \n");
        String mat= scan.nextLine();
        System.out.println("Indique a sua velocidade média: \n");
        int velocidade = scan.nextInt();
        System.out.println("Indique o factor de fiabilidade do veiculo: \n");
        int factorF = scan.nextInt();
        System.out.println("Indique o preço base por kilometro: \n");
        double preco = scan.nextDouble();
        System.out.println("Indique a coordenada x do veiculo: \n");
        double x = scan.nextDouble();        
        System.out.println("Indique a coordenada y do veiculo: \n");
        double y = scan.nextDouble();
        System.out.println("O veiculo é ligeiro, carrinha ou moto? (l/c/m): \n");
        String tipo = scan.nextLine();
        Localizacao loc = new Localizacao(x,y);
        if (tipo.equals("l")) {
            Ligeiro lig = new Ligeiro(velocidade,preco,factorF,mat,loc);
            umer.getVeiculo().put(lig.getMat(),lig.clone());
        }
        else if (tipo.equals("c")) {
            Carrinha car = new Carrinha(velocidade,preco,factorF,mat,loc);
            umer.getVeiculo().put(car.getMat(),(car.clone()));
        }
        else {
            Mota mota = new Mota(velocidade,preco,factorF,mat,loc);
            umer.getVeiculo().put(mota.getMat(),(mota.clone()));
        }
    }
        
    
    private static void associaVeiculo() throws VeiculoNaoExisteException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Indique a matricula do veiculo a que se pretende associar: \n");
        String mat = scan.nextLine();
        Veiculo v = umer.getVeiculo().get(mat).clone();
        if(v == null){throw new VeiculoNaoExisteException("Veiculo não existe!");}
        else {
            Motorista m = (Motorista) umer.getUser();
            m.setVeiculo(v);
        }
    }
    
    private static void listaMotoristaEmp() {
       List<Motorista> mot = new ArrayList<Motorista>();
       Motorista m = (Motorista) umer.getUser();
       mot = m.getEmpresa().getMotoristas();
       for(Motorista mo : mot)
            System.out.println(mo.getNome()+"\n");
    }
    
    private static void listaVeiculoEmp() {
       List<Veiculo> mot = new ArrayList<Veiculo>();
       Motorista m = (Motorista) umer.getUser();
       mot = m.getEmpresa().getTaxis();
       for(Veiculo v : mot)
            System.out.println(v.getMat()+"\n");
    }
    
    private static void desassociaEmpresa() {
        Scanner scan = new Scanner(System.in); 
        System.out.println("Digite o nome da empresa a que se pretende associar:\n");
        String nome=scan.nextLine();
        Motorista m = (Motorista) umer.getUser();
        if(umer.getEmpresa().get(nome) != null){
            m.setEmpresa(null);
            m.getEmpresa().getMotoristas().remove(m);   
        }
        else{
            throw new EmpresaNaoExisteException("Esta Empresa não existe!");
        }
        m.setEmpresa(null);
        m.getEmpresa().getMotoristas().remove(m);
    }
}

