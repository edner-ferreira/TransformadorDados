import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.json.simple.JSONObject;

public class TransformaTxt {
	private static String pathTelefone = "/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/Telefone2";
	private static String pathArqComp = "/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base4/NomesTelefones.vcf";
	private static String pathArqCompJson = "/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base4/NomesTelefones.json";
	
	public static ArrayList<String> leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		ArrayList<String> linhas = new ArrayList<String>();
//		LinkedList<String> linhasComp = new LinkedList<String>();
		String linha;
		int i = 1;
		
		while((linha = buffRead.readLine()) != null) {
			linhas.add(linha);
//			System.out.println("linhas = " + i + " " + linhas.get(i - 1));
			i++;
		}
		Set<String> set = new HashSet<>(linhas);
		
		linhas.clear();
		linhas.addAll(set);
		buffRead.close();
		return linhas;
	}
	
//	public static void escritor() throws IOException {
//		BufferedWriter buffWrite1 = new BufferedWriter(new FileWriter(pathArqComp));
//		BufferedWriter buffWrite2 = new BufferedWriter(new FileWriter(pathArqCompJson));
//		JSONObject jsonObject = new JSONObject();
//
//		ArrayList<Contatos> contatos1 = new ArrayList<Contatos>();
//		
////		LinkedList<String> contatosNomesTel = new LinkedList<>();
//		ArrayList<String> telefones1 = new ArrayList<String>();
//		
//		telefones1.addAll(leitor(pathTelefone));
//		Iterator<String> it1 = telefones1.iterator();
//		
//		while(it1.hasNext()) {
//			Contatos contato = new Contatos();
//			contato.setTelefone(it1.next());
//			contatos1.add(contato);
//		}
//		int i = 1;
//		int mil = 1;
//		buffWrite2.append("[\n");
//		for (Contatos cont : contatos1) {
////			System.out.println(cont.getTelefone());
//			if(cont.getTelefone().startsWith("12")) {
//				System.out.println("Numero: " + i);
//				//Arquivo vcf
//				buffWrite1.append("BEGIN:VCARD\n");
//				buffWrite1.append("VERSION:2.1\n");
//				buffWrite1.append("N:;" + (mil + i) + ";;;\n");
//				buffWrite1.append("FN:" + (mil + i) + "\n");
//				buffWrite1.append("TEL;CELL:" + cont.getTelefone() + "\n");
//				buffWrite1.append("END:VCARD\n");
//				//Arquivo json
//				jsonObject.put("number", "55" + cont.getTelefone());
//				jsonObject.put("name", (mil + i));
//				buffWrite2.append(jsonObject.toJSONString() + ",\n");
//				i++;
//			}
//			
//		}
//		buffWrite2.append("]");
//		buffWrite1.close();
//		buffWrite2.close();
//		
//	}
	
	public static void escritor() throws IOException {
		BufferedWriter buffWrite1 = new BufferedWriter(new FileWriter(pathArqComp));
		BufferedWriter buffWrite2 = new BufferedWriter(new FileWriter(pathArqCompJson));
		JSONObject jsonObject = new JSONObject();

		
//		LinkedList<String> nomes1 = new LinkedList<String>();
		ArrayList<String> telefones1 = new ArrayList<String>();
//		LinkedList<String> nomeReversos1 = new LinkedList<String>();
//		LinkedList<String> telSem55 = new LinkedList<String>();
		ArrayList<Contatos> contatos1 = new ArrayList<Contatos>();
		
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
			
//			if(cont.getTelefone().startsWith("12")) {
			if(!cont.getTelefone().equals("")) {
				System.out.println("Numero: " + k + "Cel: " + cont.getTelefone());
				//Arquivo vcf
				buffWrite1.append("BEGIN:VCARD\n");
				buffWrite1.append("VERSION:2.1\n");
				buffWrite1.append("N:;" + k + ";;;\n");
				buffWrite1.append("FN:" + k + "\n");
				buffWrite1.append("TEL;CELL:" + cont.getTelefone() + "\n");
				buffWrite1.append("END:VCARD\n");
//				//Arquivo json
				jsonObject.put("number", "55" + cont.getTelefone());
				jsonObject.put("name", k);
				buffWrite2.append(jsonObject.toJSONString() + ",\n");
				
				auxvcf +="BEGIN:VCARD\n" 
				+ "VERSION:2.1\n" 
				+ "N:;" + k + ";;;\n"
				+ "FN:" + k + "\n"
				+ "TEL;CELL:" + cont.getTelefone() + "\n"
				+ "END:VCARD\n";
				
				auxjson += jsonObject.toJSONString() + ",";
//			}
//			else {
//				break;
//			}
			
				if(i >= 50 || cont.getTelefone().equals(contatos1.get(contatos1.size() -1).getTelefone())) {
					
					//System.out.println(auxjson.toString());
					
					BufferedWriter buffWriteComp1 = new BufferedWriter(new FileWriter("/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base4/NomesTelefones" + j + ".vcf"));
					BufferedWriter buffWriteCompjson2 = new BufferedWriter(new FileWriter("/home/richard/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/basedados/EmbalaShop/base4/NomesTelefones" + j + ".json"));
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
	
	
}
