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
import java.time.LocalDate;

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
                                 "Faturado por Empresa",
                                 "Faturado por Veiculo",
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
                case 1: try{avaliaMotorista();
                            break;
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println(e);
                        }
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
                case 5: registaViagem();
                        break;
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
                case 7: faturadoEmp();
                        break;
                case 8: try{faturadoVeic();
                            break;}
                        catch(VeiculoNaoExisteException e){
                            System.out.println(e);
                        }
                        break;
                case 9: registaViagem();
                        break;
                case 10: sinalizaDisp();
                        break;
                case 11: desassociaEmpresa();
                        break;
                case 12: umer.fechaSessao();
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
    
    private static void avaliaMotorista() throws UtilizadorNaoExisteException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o email do motorista:");
        String email=scan.nextLine();
        System.out.println("Classifique o seu motorista (de 0 a 100): ");
        int c = scan.nextInt();
        if(c>0 && c<100){
            Motorista m = (Motorista) umer.getUtilizadores().get(email);
            if(m == null){throw new UtilizadorNaoExisteException("Esse motorista não está registado");}
            m.classMotorista(c);
        }
        else{
            System.out.println("A avaliação não se encontra dentro dos limites");
        }
    }
    
    private static void consultaHistorico() {
       Scanner scan = new Scanner(System.in);
       Historico h = umer.getUser().getHistorico();
       int diaIni,mesIni,anoIni,diaFim,mesFim,anoFim;
       
       System.out.println("Insira uma data para o ínicio do histórico: ");
       System.out.println("Dia: ");
       diaIni = scan.nextInt();
       System.out.println("Mês: ");
       mesIni = scan.nextInt();
       System.out.println("Ano: ");
       anoIni = scan.nextInt();
       
       LocalDate l1 = LocalDate.of(anoIni,mesIni,diaIni);
       
       System.out.println("Insira a data para o fim do histórico: ");
       System.out.println("Dia: ");
       diaFim = scan.nextInt();
       System.out.println("Mês: ");
       mesFim = scan.nextInt();
       System.out.println("Ano: ");
       anoFim = scan.nextInt();
       
       LocalDate l2 = LocalDate.of(anoFim,mesFim,diaFim);
       
       
       List<Viagem> trips = new ArrayList<Viagem>();
       trips = h.getBetween(l1,l2);
       for(int i=0;i<10;i++){
           System.out.println(trips.get(i));
       }        
    }
    
    
    private static void associaMotoristaEmp() throws UtilizadorNaoExisteException, EmpresaNaoExisteException{        

        Scanner scan = new Scanner(System.in); 
        System.out.println("Digite o nome da empresa a que se pretende associar:");
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
        System.out.println("Digite o nome da empresa a que pretende associar:");
        String nome=scan.nextLine();
        System.out.println("Indique a matricula do veiculo que pretende associar: ");
        String mat = scan.nextLine();
        
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
        System.out.println("Indique a matricula do veiculo a registar: ");
        String mat= scan.nextLine();
        System.out.println("Indique a sua velocidade média: ");
        int velocidade = scan.nextInt();
        System.out.println("Indique o factor de fiabilidade do veiculo: ");
        int factorF = scan.nextInt();
        System.out.println("Indique o preço base por kilometro: ");
        double preco = scan.nextDouble();
        System.out.println("Indique a coordenada x do veiculo: ");
        double x = scan.nextDouble();        
        System.out.println("Indique a coordenada y do veiculo: ");
        double y = scan.nextDouble();
        System.out.println("O veiculo é ligeiro, carrinha ou moto? (l/c/m): ");
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
        else if(tipo.equals("m")){
            Mota mota = new Mota(velocidade,preco,factorF,mat,loc);
            umer.getVeiculo().put(mota.getMat(),(mota.clone()));
        }
        else{
            System.out.println("Esse tipo de veículo não está disponível!");
        }
    }
        
    
    private static void associaVeiculo() throws VeiculoNaoExisteException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Indique a matricula do veiculo a que se pretende associar: ");
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

        Motorista m = (Motorista) umer.getUser();
        m.getEmpresa().getMotoristas().remove(m);   
        m.setEmpresa(null);


    }
    
    private static void faturadoEmp() {
       Scanner scan = new Scanner(System.in);
       Motorista m = (Motorista) umer.getUser();
       List<Motorista> mot = new ArrayList<Motorista>();
       mot=m.getEmpresa().getMotoristas();
       double total=0;
       
       int diaIni,mesIni,anoIni,diaFim,mesFim,anoFim;
       
       System.out.println("Insira uma data para o ínicio do histórico: ");
       System.out.println("Dia: ");
       diaIni = scan.nextInt();
       System.out.println("Mês: ");
       mesIni = scan.nextInt();
       System.out.println("Ano: ");
       anoIni = scan.nextInt();
       
       LocalDate l1 = LocalDate.of(anoIni,mesIni,diaIni);
       
       System.out.println("Insira a data para o fim do histórico: ");
       System.out.println("Dia: ");
       diaFim = scan.nextInt();
       System.out.println("Mês: ");
       mesFim = scan.nextInt();
       System.out.println("Ano: ");
       anoFim = scan.nextInt();
       
       LocalDate l2 = LocalDate.of(anoFim,mesFim,diaFim);
       
       for(Motorista entry : mot){
           total+=entry.getHistorico().getBetween(l1,l2).stream().mapToDouble(Viagem::getPreco).sum();
        }

       for(int i=0;i<10;i++)
           System.out.println("O total faturado pela empresa nesse periodo foi de: "+total);
        
    }
    
    private static void faturadoVeic() throws VeiculoNaoExisteException{
       Scanner scan = new Scanner(System.in);
       Motorista m = (Motorista) umer.getUser();
       List<Veiculo> taxis = new ArrayList<Veiculo>();
       taxis=m.getEmpresa().getTaxis();
       double total=0;
       
       int diaIni,mesIni,anoIni,diaFim,mesFim,anoFim;
       
       System.out.println("Indique a matricula do veiculo que pretende consultar: ");
       String mat=scan.nextLine();
       
       if(taxis.stream().anyMatch(f->f.getMat().equals(mat))) {throw new VeiculoNaoExisteException("Veiculo não existe!");}
       Veiculo v = umer.getVeiculo().get(mat);
       
        System.out.println("Insira uma data para o ínicio do histórico: ");
       System.out.println("Dia: ");
       diaIni = scan.nextInt();
       System.out.println("Mês: ");
       mesIni = scan.nextInt();
       System.out.println("Ano: ");
       anoIni = scan.nextInt();
       
       LocalDate l1 = LocalDate.of(anoIni,mesIni,diaIni);
       
       System.out.println("Insira a data para o fim do histórico: ");
       System.out.println("Dia: ");
       diaFim = scan.nextInt();
       System.out.println("Mês: ");
       mesFim = scan.nextInt();
       System.out.println("Ano: ");
       anoFim = scan.nextInt();
       
       LocalDate l2 = LocalDate.of(anoFim,mesFim,diaFim);
       
       total+=v.getHistorico().getBetween(l1,l2)
               .stream().mapToDouble(Viagem::getPreco).sum();
        

       for(int i=0;i<10;i++)
           System.out.println("O total faturado pela empresa nesse periodo foi de: "+total);
        
    }
    /*
    private static void solTaxiProx() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Indique a coordenada x do local de entrada: ");
        double xi = scan.nextDouble();        
        System.out.println("Indique a coordenada y do local de entrada: ");
        double yi = scan.nextDouble();
        Veiculo v = new Veiculo();
        double distmin=-1;
        Localizacao loc = new Localizacao(xi,yi);
        for (Veiculo a : umer.getVeiculo().values()) {
            double dist =a.getLocalizacao().calculaDist(loc);
            if(distmin==-1 || distmin>dist) {
                distmin=dist;
                v=a.clone();
            }
                
        }
    }*/
    
    private static void registaViagem() {
        Scanner scan = new Scanner(System.in); 
        System.out.println("Indique o email do motorista: ");
        String motorista = scan.nextLine();
        System.out.println("Indique o email do cliente: ");
        String cliente = scan.nextLine();
        System.out.println("Indique a matricula do veiculo: ");
        String mat= scan.nextLine();
        
        System.out.println("Indique a coordenada x inicial da viagem: ");
        double xi = scan.nextDouble();        
        System.out.println("Indique a coordenada y inicial da viagem: ");
        double yi = scan.nextDouble();
        Localizacao li = new Localizacao(xi,yi);
        
        System.out.println("Indique a coordenada x final da viagem: ");
        double xf = scan.nextDouble();        
        System.out.println("Indique a coordenada y final da viagem: ");
        double yf = scan.nextDouble();
        Localizacao lf = new Localizacao(xf,yf);
                
        System.out.println("Dia: ");
        int diaIni = scan.nextInt();
        System.out.println("Mês: ");
        int mesIni = scan.nextInt();
        System.out.println("Ano: ");
        int anoIni = scan.nextInt();
        LocalDate data = LocalDate.of(anoIni,mesIni,diaIni);
        
        Motorista m = (Motorista) umer.getUtilizadores().get(motorista);
        Cliente c = (Cliente) umer.getUtilizadores().get(cliente);
        Veiculo v = umer.getVeiculo().get(mat);
        double preco = v.getPreco()*li.calculaDist(lf);
        Viagem viagem= new Viagem(preco,li,lf,m,c,v,data);
        
        m.getHistorico().addViagem(viagem);
        c.getHistorico().addViagem(viagem);
        v.getHistorico().addViagem(viagem);
        
        m.setDisp(true);
    }
}

