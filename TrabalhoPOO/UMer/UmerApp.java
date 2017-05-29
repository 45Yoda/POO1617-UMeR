import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Set;
import java.lang.Object;
import java.io.Serializable;
import java.io.IOException;
import java.util.Map;

public class UmerApp implements Serializable
{
    private UmerApp(){}
    private static Utilizador uti=null;
    private static UmeR umer;
    private static Menu mMain, mCliente, mSolicitar, mRegistos, mMotorista, mMotoristaEmp, mVeiculo;
        
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
}
