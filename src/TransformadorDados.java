import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class TransformadorDados {
	
	//private static String pathNomes = "/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/Nomes";
	private static String pathTelefone = "/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/Telefone";
	private static String pathArqComp = "/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base2/NomesTelefones.vcf";
	private static String pathArqCompJson = "/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base2/NomesTelefones.json";
//	private static String pathNomeTel = "/home/bocao/Documentos/whatsappWeb/meuzap/basedados/ListaContatoEmbalaShop.txt";
	
	private HashMap<Contatos, Contatos> contatoMap = new HashMap<>();
	
	public static LinkedList<String> leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		LinkedList<String> linhas = new LinkedList<String>();
		String linha;
//		int i = 1;
		
		while((linha = buffRead.readLine()) != null) {
			linhas.add(linha);
			//System.out.println("linhas = " + i + " " + linhas.get(i - 1));
//			i++;
		}
		buffRead.close();
		return linhas;
	}
	
	public static void escritor() throws IOException {
		BufferedWriter buffWrite1 = new BufferedWriter(new FileWriter(pathArqComp));
		BufferedWriter buffWrite2 = new BufferedWriter(new FileWriter(pathArqCompJson));
		JSONObject jsonObject = new JSONObject();

		
//		LinkedList<String> nomes1 = new LinkedList<String>();
		LinkedList<String> telefones1 = new LinkedList<String>();
//		LinkedList<String> nomeReversos1 = new LinkedList<String>();
//		LinkedList<String> telSem55 = new LinkedList<String>();
		LinkedList<Contatos> contatos1 = new LinkedList<Contatos>();
		
//		LinkedList<String> contatosNomesTel = new LinkedList<>();
		
		
		
//		contatosNomesTel.addAll(leitor(pathNomeTel));
//		System.out.println(contatosNomesTel.size());
//		nomes1.addAll(leitor(pathNomes));
//		nomeReversos1.addAll(nomeReverso(nomes1));
		telefones1.addAll(leitor(pathTelefone));
//		telSem55.addAll(retirarNum(telefones1));
		
//		nomes1.clear();
//		nomes1.addAll(nomeReversos1);
//		
//		telefones1.clear();
//		telefones1.addAll(telSem55);
		
//		
		
//		
//		Iterator<String> it1 = nomes1.iterator();
		Iterator<String> it2 = telefones1.iterator();
//		Iterator<String> it3 = nomeReversos1.iterator();
		//System.out.println(nomes.size() + " " + telefones.size() + " " + nomeReversos.size());
		
		while(it2.hasNext()) {
//			System.out.println("ola3");
//			String nome = it3.next();
			Contatos contato = new Contatos();
//			contato.setNome(it1.next());
			contato.setTelefone(it2.next());
//			contato.setNomeReverso(it3.next());
			contatos1.add(contato);
		}
		int i = 1;
		int j = 1;
		int k = 1;
		buffWrite2.append("[\n");
		String auxvcf ="";
		String auxjson = "";
		
		for (Contatos cont : contatos1) {
			
			if(cont.getTelefone().startsWith("12")) {
				System.out.println("Numero: " + k);
				//Arquivo vcf
				buffWrite1.append("BEGIN:VCARD\n");
				buffWrite1.append("VERSION:2.1\n");
				buffWrite1.append("N:;" + i + ";;;\n");
				buffWrite1.append("FN:" + i + "\n");
				buffWrite1.append("TEL;CELL:" + cont.getTelefone() + "\n");
				buffWrite1.append("END:VCARD\n");
//				//Arquivo json
				jsonObject.put("number", "55" + cont.getTelefone());
				jsonObject.put("name", i);
				buffWrite2.append(jsonObject.toJSONString() + ",\n");
				
				auxvcf +="BEGIN:VCARD\n" 
				+ "VERSION:2.1\n" 
				+ "N:;" + i + ";;;\n"
				+ "FN:" + i + "\n"
				+ "TEL;CELL:" + cont.getTelefone() + "\n"
				+ "END:VCARD\n";
				
				auxjson += jsonObject.toJSONString() + ",";
//			}
//			else {
//				break;
//			}
			
				if(i >= 50 || cont.equals(contatos1.getLast())) {
					
					//System.out.println(auxjson.toString());
					
					BufferedWriter buffWriteComp1 = new BufferedWriter(new FileWriter("/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base2/NomesTelefones" + j + ".vcf"));
					BufferedWriter buffWriteCompjson2 = new BufferedWriter(new FileWriter("/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base2/NomesTelefones" + j + ".json"));
					buffWriteComp1.append(auxvcf.toString());
					buffWriteCompjson2.append("[" + auxjson.toString() + "]");
					buffWriteComp1.close();
					buffWriteCompjson2.close();
					j++;
					i=0;
					auxvcf = "";
					auxjson = "";
	//				jsonObject.clear();
				}
				i++;
				k++;
			}
		}
		buffWrite2.append("]");
		buffWrite1.close();
		buffWrite2.close();
		
	}
	
//	public static LinkedList<String> nomeReverso(LinkedList<String> nome){
//		
//		int j = 0;
//		
//		LinkedList<String> nomeRevertido = new LinkedList<String>();
// 		Iterator<String> it1 = nome.iterator();
//		
//		while(it1.hasNext()) {
//			
//			String aux1 = it1.next();
//			String[] reverso = aux1.split("\\s");
//
//			nomeRevertido.add(reverso[0]);
//			
//		}
////		
////		
////		System.out.println("olaR");
//		return nomeRevertido;
//	}
//	
//public static LinkedList<String> retirarNum(LinkedList<String> telefone){
//		
//		int j = 0;
//		
//		LinkedList<String> telNumRetirar = new LinkedList<String>();
// 		Iterator<String> it1 = telefone.iterator();
//		
//		while(it1.hasNext()) {
//			
//			String aux1 = it1.next();
//			if(aux1.startsWith("55")) {
//				String[] reverso = aux1.split("^55");
//
//				telNumRetirar.add(reverso[1]);
////				System.out.println(reverso[1]);
//			}else {
//				telNumRetirar.add(aux1);
////				System.out.println(aux1);
//			}
////			System.out.println(j+1);
////			j++;
//									
//		}
//		
//		
//		System.out.println("olaT");
//		return telNumRetirar;
//	}
	
	public static void main(String[] args) {
		
		try {
//			escritor();
			TransformaTxt.escritor();
			System.out.println("Foi!!!");
		} catch (Exception er) {
			System.out.print(er);
		}
	}

}
