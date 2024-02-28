package classes.DTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import classes.BO.CidadaoBO;

public class Main {
	public static void main(String[] args) {
		
		Cidadao cidadao = new Cidadao(2023004505);
		
		CidadaoBO cidBO = new CidadaoBO();
		
		cidadao = cidBO.procurarId(cidadao);
		
		System.out.println(cidadao);
		System.out.println("Olá, mundo!");
		
		/*
		Scanner entrada = new Scanner(System.in);
		
		// Cria��o do Administrador
		
		LocalDate data = LocalDate.parse("1978-04-24");
		Administrador admin1 = new Administrador(2023261023, "Igor Kammer Grahl", data, "180.719.870-00", "12345");
		Cidadao cid1 = new Cidadao(2023261023, "Igor Kammer Grahl", data, "180.719.870-00");
		
		ArrayList<Administrador> admins = new ArrayList<Administrador>();
		admins.add(admin1);
		
		// Cria��o da Unidade de Sa�de
		
		UnidadeSaude usItuporanga = new UnidadeSaude("Unidade Sanit�ria Centro", "Rua Em�lio Altemburg", "Centro", "Ituporanga", "Santa Catarina", "(47) 3533-3176");
		
		ArrayList<UnidadeSaude> unidades = new ArrayList<UnidadeSaude>();
		unidades.add(usItuporanga);
		
		// Cria��o dos Funcion�rios
		
		data = LocalDate.parse("1953-09-13");
		Funcionario func1 = new Funcionario(2009210109, "Irina", data, "050.120.050-00", "534", usItuporanga);
		Cidadao cid2 = new Cidadao(2009210109, "Irina", data, "050.120.050-00");
		
		data = LocalDate.parse("2005-02-14");
		Funcionario func2 = new Funcionario(19770525, "Rodrigo Curv�llo", data, "400.289.220-00", "1234", usItuporanga);
		Cidadao cid3 = new Cidadao(19770525, "Rodrigo Curv�llo", data, "400.289.220-00");
		
		ArrayList<Funcionario> funcs = new ArrayList<Funcionario>();
		funcs.add(func1);
		funcs.add(func2);
		
		// Cria��o do Cidad�o
		
		data = LocalDate.parse("2005-01-05");
		Cidadao cid4 = new Cidadao(2023004505, "Pedro Ryan Coelho Iplinski", data, "123.456.789-10");
		
		ArrayList<Cidadao> cidadaos = new ArrayList<Cidadao>();
		cidadaos.add(cid1);
		cidadaos.add(cid2);
		cidadaos.add(cid3);
		cidadaos.add(cid4);
		
		Lote lote1 = new Lote("210200", "Contra Gripe", "Butantan", usItuporanga);
		Lote lote2 = new Lote("FN9509", "Contra Covid-19", "Pfizer", usItuporanga);
		
		ArrayList<Lote> lotes = new ArrayList<Lote>();
		lotes.add(lote1);
		lotes.add(lote2);
		
		data = LocalDate.parse("2021-06-11");
		RegistroVacina registro1 = new RegistroVacina(data, lotes.get(0), cidadaos.get(3), funcs.get(0), usItuporanga);
		
		data = LocalDate.parse("2022-07-27");
		RegistroVacina registro2 = new RegistroVacina(data, lotes.get(1), cidadaos.get(3), funcs.get(1), 3, usItuporanga);
		
		ArrayList<RegistroVacina> registros = new ArrayList<RegistroVacina>();
		registros.add(registro1);
		registros.add(registro2);
		
		// Tela inicial
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		int opcao = 1;
		int resp;
		
		while(opcao != 0) {
		
			System.out.println("\nBem-vindo(a) ao Conecte SUS \n");
			
			do {
				System.out.println("O que voc� quer fazer?");
				System.out.println("(1) Login \n(0) Encerrar sistema");
				opcao = entrada.nextInt();
				if(opcao < 0 || opcao > 1)
					System.out.println("\nErro: Op��o inv�lida\n");
			} while(opcao < 0 || opcao > 1);
			
			if(opcao > 0) {
				if(opcao == 1) {
					
					// Login
		
					System.out.println("\nLogin\n");
					
					do {
						System.out.println("Escolha a forma de login:");
						System.out.println("(1) Cidad�o \n(2) Funcion�rio \n(3) Administrador \n(0) Voltar");
						resp = entrada.nextInt();
						if(resp < 0 || resp > 3)
							System.out.println("\nErro: Op��o inv�lida\n");
					} while(resp < 0 || resp > 3);
					
					if(resp > 0) {
					
						int numeroCNS;
						String senha;
						int usuario = 0;
						int cont = 0;
						boolean condicao = true;
						int cidad = 0;
						boolean condicao1 = true;
						
						if(resp == 1) {
							
							// Login como cidad�o
							
							System.out.println("\nLogin como Cidad�o: \n");
							
							while(condicao) {
								System.out.println("N�mero de sua Carteira Nacional de Sa�de: ");
								numeroCNS = entrada.nextInt();
								for(int i = 0; i < cidadaos.size(); i++) {
									if(cidadaos.get(i).getNumeroCNS() == numeroCNS) {
										condicao = false;
										usuario = i;
									}
								}
								if(condicao)
									System.out.println("\nErro: N�mero de CNS inexistente. Por favor, insira novamente\n");
							}
							
							System.out.println("\nLogin realizado com sucesso!\n");
							
							// Menu do cidad�o
							
							System.out.println("Bem-vindo(a), " + cidadaos.get(usuario).getNome());
							
							condicao = true;
							
							while(condicao) {
								do {
									System.out.println("\nQual a��o gostaria de realizar?");
									System.out.println("(1) Consultar minhas vacinas");
									System.out.println("(0) Sair");
									resp = entrada.nextInt();
									if(resp < 0 || resp > 1)
										System.out.println("\nErro: Op��o inv�lida\n");
								} while(resp < 0 || resp > 1);
								
								// Consulta de vacinas do cidad�o
								
								if(resp == 1) {
									
									System.out.println("\nMinhas Vacinas:");
									
									for(int i = 0; i < registros.size(); i++) {
										if(cidadaos.get(usuario).getNumeroCNS() == registros.get(i).getCidadao().getNumeroCNS()) {
											System.out.println("\nData: " + registros.get(i).getData().format(dataFormato));
											System.out.println("Vacina: " + registros.get(i).getLote().getNomeVacina());
											System.out.println("Lote: " + registros.get(i).getLote().getCodigo());
											System.out.println("Laborat�rio: " + registros.get(i).getLote().getLaboratorio());
											System.out.println("Vacinador(a): " + registros.get(i).getVacinador().getNome());
											if(registros.get(i).getDose() != 0)
												System.out.println("Dose: " + registros.get(i).getDose() + "�");
											System.out.println("Unidade de Sa�de: " + registros.get(i).getUnidadeSaude().getNome() + " (" + registros.get(i).getUnidadeSaude().getCidade() + ")");
											cont++;
										}
									}
									if(cont == 0)
										System.out.println("\nNenhum registro de vacina encontrado. \n");
									
									cont = 0;
								} else
									condicao = false;
							}
							
						} else if(resp == 2) {
							
							// Login como funcion�rio
							
							System.out.println("\nLogin como Funcion�rio: \n");
							
							while(condicao) {
								System.out.println("N�mero de sua Carteira Nacional de Sa�de: ");
								numeroCNS = entrada.nextInt();
								
								entrada.nextLine();
								
								System.out.println("Senha: ");
								senha = entrada.nextLine();
								
								for(int i = 0; i < funcs.size(); i++) {
									if(funcs.get(i).getNumeroCNS() == numeroCNS && funcs.get(i).getSenha().equals(senha)) {
										condicao = false;
										usuario = i;
									}
								}
								if(condicao)
									System.out.println("\nErro: N�mero de CNS e/ou senha inv�lida\n");
							}
							
							System.out.println("\nLogin realizado com sucesso!\n");
							
							// Menu do funcion�rio
							
							System.out.println("Bem-vindo(a), " + funcs.get(usuario).getNome());
							
							condicao = true;
							
							while(condicao) {
								
								condicao1 = true;
								
								do {
									System.out.println("\nQual a��o gostaria de realizar?");
									System.out.println("(1) Consultar suas vacinas");
									System.out.println("(2) Consultar vacinas de cidad�o");
									System.out.println("(3) Cadastrar registro de vacina");
									System.out.println("(4) Consultar lotes");
									System.out.println("(5) Cadastrar lote");
									System.out.println("(0) Sair");
									resp = entrada.nextInt();
									if(resp < 0 || resp > 5)
										System.out.println("\nErro: Op��o inv�lida");
								} while(resp < 0 || resp > 5);
								
								if(resp == 1) {
									
									// Consulta de vacinas do funcion�rio
									
									System.out.println("\nMinhas Vacinas:");
									
									for(int i = 0; i < registros.size(); i++) {
										if(funcs.get(usuario).getNumeroCNS() == registros.get(i).getCidadao().getNumeroCNS()) {
											System.out.println("\nData: " + registros.get(i).getData().format(dataFormato));
											System.out.println("Vacina: " + registros.get(i).getLote().getNomeVacina());
											System.out.println("Lote: " + registros.get(i).getLote().getCodigo());
											System.out.println("Laborat�rio: " + registros.get(i).getLote().getLaboratorio());
											System.out.println("Vacinador(a): " + registros.get(i).getVacinador().getNome());
											if(registros.get(i).getDose() != 0)
												System.out.println("Dose: " + registros.get(i).getDose() + "�");
											System.out.println("Unidade de Sa�de: " + registros.get(i).getUnidadeSaude().getNome() + " (" + registros.get(i).getUnidadeSaude().getCidade() + ")");
											cont++;
										}
									}
									if(cont == 0)
										System.out.println("\nNenhum registro de vacina encontrado.");
									
									cont = 0;
									
									do {
										System.out.println("\nO que voc� quer fazer?");
										System.out.println("(1) Voltar \n(0) Sair (Desconectar)");
										resp = entrada.nextInt();
										if(resp < 0 || resp > 1)
											System.out.println("\nErro: Op��o inv�lida\n");
									} while(resp < 0 || resp > 1);
									
									if(resp == 0)
										condicao = false;
								} else if(resp == 2) {
									
									// Consulta de vacinas do cidad�o
									
									while(condicao1) {
										System.out.println("\nInsira o n�mero da Carteira Nacional de Sa�de do cidad�o: ");
										numeroCNS = entrada.nextInt();
										for(int i = 0; i < cidadaos.size(); i++) {
											if(cidadaos.get(i).getNumeroCNS() == numeroCNS) {
												condicao1 = false;
												cidad = i;
											}
										}
										if(condicao1)
											System.out.println("\nErro: N�mero de CNS inexistente. Por favor, insira novamente");
									}
									
									System.out.println("\nCidad�(o): " + cidadaos.get(cidad).getNome());
									
									for(int i = 0; i < registros.size(); i++) {
										if(cidadaos.get(cidad).getNumeroCNS() == registros.get(i).getCidadao().getNumeroCNS()) {
											System.out.println("\nData: " + registros.get(i).getData().format(dataFormato));
											System.out.println("Vacina: " + registros.get(i).getLote().getNomeVacina());
											System.out.println("Lote: " + registros.get(i).getLote().getCodigo());
											System.out.println("Laborat�rio: " + registros.get(i).getLote().getLaboratorio());
											System.out.println("Vacinador(a): " + registros.get(i).getVacinador().getNome());
											if(registros.get(i).getDose() != 0)
												System.out.println("Dose: " + registros.get(i).getDose() + "�");
											System.out.println("Unidade de Sa�de: " + registros.get(i).getUnidadeSaude().getNome() + " (" + registros.get(i).getUnidadeSaude().getCidade() + ")");
											cont++;
										}
									}
									if(cont == 0)
										System.out.println("\nNenhum registro de vacina encontrado.");
									
									cont = 0;
									
									do {
										System.out.println("\nO que voc� quer fazer?");
										System.out.println("(1) Voltar \n(0) Sair (Desconectar)");
										resp = entrada.nextInt();
										if(resp < 0 || resp > 1)
											System.out.println("\nErro: Op��o inv�lida\n");
									} while(resp < 0 || resp > 1);
									
									if(resp == 0)
										condicao = false;
								} else if(resp == 3) {
									
									for(int i = 0; i < lotes.size(); i++) {
										if(funcs.get(usuario).getUnidadeSaude().getTelefone() == lotes.get(i).getUnidadeSaude().getTelefone())
											cont++;
									}
									
									if(cont > 0) {
										
										// Cadastro de registro de vacina
										
										cont = 0;
										
										String[] dataVetor = new String[3];
										
										System.out.println("\nCadastro de Registro de Vacina\n");
										
										entrada.nextLine();
										
										while(condicao1) {
											System.out.println("Data (Dia/M�s/Ano):");
											String dataVacina = entrada.nextLine();
											dataVetor = dataVacina.split("/");
											int[] dataInt = {Integer.parseInt(dataVetor[0]), Integer.parseInt(dataVetor[1]), Integer.parseInt(dataVetor[2])};
											if(dataInt[0] >= 1 && dataInt[0] <= 31 && dataInt[1] >= 1 && dataInt[1] <= 12 && dataInt[2] <= (LocalDate.now().getYear())) {
												if(dataInt[1] % 2 != 0)
													condicao1 = false;
												else {
													if(dataInt[1] == 2)
														if(dataInt[2] % 4 == 0) {
															if(dataInt[0] <= 29)
																condicao1 = false;
															else
																System.out.println("\nErro: Data inv�lida\n");
														} else
															if(dataInt[0] <= 28)
																condicao1 = false;
															else
																System.out.println("\nErro: Data inv�lida\n");
													else {
														if(dataInt[0] <= 30)
															condicao1 = false;
														else
															System.out.println("\nErro: Data inv�lida\n");
													}
												}
											} else
												System.out.println("\nErro: Data inv�lida\n");
										}
										
										condicao1 = true;
										
										int lote;
										
										do {
											System.out.println("\nLote:");
											for(int i = 0; i < lotes.size(); i++) {
												if(funcs.get(usuario).getUnidadeSaude().getTelefone() == lotes.get(i).getUnidadeSaude().getTelefone())
													System.out.println("(" + (i + 1) + ") " + lotes.get(i).getNomeVacina() + " (" + lotes.get(i).getCodigo() + ")");
											}
											lote = entrada.nextInt();
											if(lote < 1 || lote > lotes.size())
												System.out.println("\nErro: Op��o inv�lida");
										} while(lote < 1 || lote > lotes.size());
										
										lote--;
										
										int cidadao = 0;
										
										while(condicao1) {
											System.out.println("\nN�mero da Carteira Nacional de Sa�de do Cidad�o:");
											numeroCNS = entrada.nextInt();
											for(int i = 0; i < cidadaos.size(); i++) {
												if(cidadaos.get(i).getNumeroCNS() == numeroCNS) {
													condicao1 = false;
													cidadao = i;
												}
											}
											if(condicao1)
												System.out.println("\nErro: N�mero de CNS inexistente. Por favor, insira novamente");
										}
										
										int vacinador;
										
										do {
											System.out.println("\nVacinador(a):");
											for(int i = 0; i < funcs.size(); i++)
												System.out.println("(" + (i + 1) + ") " + funcs.get(i).getNome());
											vacinador = entrada.nextInt();
											if(vacinador < 1 || vacinador > funcs.size())
												System.out.println("\nErro: Op��o inv�lida");
										} while(vacinador < 1 || vacinador > funcs.size());
										
										vacinador--;
										
										int dose;
										
										do {
											System.out.println("\nDose (ou digite 0 se n�o for o caso):");
											dose = entrada.nextInt();
											if(dose < 0)
												System.out.println("\nErro: Valor inv�lido");
										} while(dose < 0);
										
										data = LocalDate.parse(dataVetor[2] + "-" + dataVetor[1] + "-" + dataVetor[0]);
										RegistroVacina registro = new RegistroVacina(data, lotes.get(lote), cidadaos.get(cidadao), funcs.get(vacinador), dose, funcs.get(usuario).getUnidadeSaude());
										registros.add(registro);
										
										System.out.println("\nCadastro realizado com sucesso!\n");
										
										do {
											System.out.println("O que voc� quer fazer?");
											System.out.println("(1) Voltar \n(0) Sair (Desconectar)");
											resp = entrada.nextInt();
											if(resp < 0 || resp > 1)
												System.out.println("\nErro: Op��o inv�lida\n");
										} while(resp < 0 || resp > 1);
										
										if(resp == 0)
											condicao = false;
										
									} else
										System.out.print("\nNenhum lote foi encontrado. Primeiro, cadastre um lote de vacina \n");
								
								} else if(resp == 4) {
									
									// Consulta de lotes
									
									System.out.println("\nLotes de Vacina de " + funcs.get(usuario).getUnidadeSaude().getNome());
									
									for(int i = 0; i < lotes.size(); i++) {
										if(funcs.get(usuario).getUnidadeSaude().getTelefone() == lotes.get(i).getUnidadeSaude().getTelefone()) {
											System.out.println("\nC�digo: " + lotes.get(i).getCodigo());
											System.out.println("Vacina: " + lotes.get(i).getNomeVacina());
											System.out.println("Laborat�rio: " + lotes.get(i).getLaboratorio());
										}
									}
									
									do {
										System.out.println("\nO que voc� quer fazer?");
										System.out.println("(1) Voltar \n(0) Sair (Desconectar)");
										resp = entrada.nextInt();
										if(resp < 0 || resp > 1)
											System.out.println("\nErro: Op��o inv�lida");
									} while(resp < 0 || resp > 1);
									
									if(resp == 0)
										condicao = false;
								
								} else if(resp == 5) {
									
									// Cadastro de lote
									
									System.out.println("\nCadasto de Lotes de Vacina \n");
									
									entrada.nextLine();
									
									System.out.println("C�digo:");
									String codigo = entrada.nextLine();
									
									System.out.println("\nVacina:");
									String vacina = entrada.nextLine();
									
									System.out.println("\nLaborat�rio:");
									String laboratorio = entrada.nextLine();
									
									Lote lote = new Lote(codigo, vacina, laboratorio, funcs.get(usuario).getUnidadeSaude());
									lotes.add(lote);
									
									System.out.println("\nCadastro realizado com sucesso!");
									
									do {
										System.out.println("\nO que voc� quer fazer?");
										System.out.println("(1) Voltar \n(0) Sair (Desconectar)");
										resp = entrada.nextInt();
										if(resp < 0 || resp > 1)
											System.out.println("\nErro: Op��o inv�lida");
									} while(resp < 0 || resp > 1);
									
									if(resp == 0)
										condicao = false;
								
								} else
									condicao = false;
							}
						} else {
							
							// Login como administrador
							
							System.out.println("\nLogin como Administrador: \n");
							
							while(condicao) {
								System.out.println("N�mero de sua Carteira Nacional de Sa�de: ");
								numeroCNS = entrada.nextInt();
								
								entrada.nextLine();
								
								System.out.println("Senha: ");
								senha = entrada.nextLine();
								
								for(int i = 0; i < admins.size(); i++) {
									if(admins.get(i).getNumeroCNS() == numeroCNS && admins.get(i).getSenha().equals(senha)) {
										condicao = false;
										usuario = i;
									}
								}
								if(condicao)
									System.out.println("\nErro: N�mero de CNS e/ou senha inv�lida\n");
							}
							
							System.out.println("\nLogin realizado com sucesso!\n");
							
							// Menu do administrador
							
							System.out.println("Bem-vindo(a), " + admins.get(usuario).getNome());
							
							condicao = true;
							
							while(condicao) {
								
								do {
									System.out.println("\nQual a��o gostaria de realizar?");
									System.out.println("(1) Consultar Unidades de Sa�de");
									System.out.println("(2) Cadastrar Unidade de Sa�de");
									System.out.println("(0) Sair");
									resp = entrada.nextInt();
									if(resp < 0 || resp > 2)
										System.out.println("\nErro: Op��o inv�lida");
								} while(resp < 0 || resp > 2);
								
								if(resp == 1) {
									
									// Consulta de Unidades de Sa�de
									
									System.out.println("\nUnidades de Sa�de");
									
									for(int i = 0; i < unidades.size(); i++) {
										System.out.println("\nNome: " + unidades.get(i).getNome());
										System.out.println("Endere�o: " + unidades.get(i).getRua() + ", " + unidades.get(i).getBairro() + " - " + unidades.get(i).getCidade() + " (" + unidades.get(i).getEstado() + ")");
										System.out.println("Telefone: " + unidades.get(i).getTelefone());
									}
									
									do {
										System.out.println("\nO que voc� quer fazer?");
										System.out.println("(1) Voltar \n(0) Sair (Desconectar)");
										resp = entrada.nextInt();
										if(resp < 0 || resp > 1)
											System.out.println("\nErro: Op��o inv�lida");
									} while(resp < 0 || resp > 1);
									
									if(resp == 0)
										condicao = false;
									
								} else if(resp == 2) {
									
									// Cadastro de Unidade de Sa�de
									
									System.out.println("\nCadastro de Unidade de Sa�de");
									
									entrada.nextLine();
									
									System.out.println("\nNome da Unidade:");
									String nomeUnidade = entrada.nextLine();
									
									System.out.println("\nRua:");
									String rua = entrada.nextLine();
									
									System.out.println("\nBairro:");
									String bairro = entrada.nextLine();
									
									System.out.println("\nCidade:");
									String cidade = entrada.nextLine();

									System.out.println("\nEstado:");
									String estado = entrada.nextLine();
									
									System.out.println("\nTelefone:");
									String telefone = entrada.nextLine();
									
									UnidadeSaude us = new UnidadeSaude(nomeUnidade, rua, bairro, cidade, estado, telefone);
									unidades.add(us);
									
									System.out.println("\nCadastro realizado com sucesso!");
									
									do {
										System.out.println("\nO que voc� quer fazer?");
										System.out.println("(1) Voltar \n(0) Sair (Desconectar)");
										resp = entrada.nextInt();
										if(resp < 0 || resp > 1)
											System.out.println("\nErro: Op��o inv�lida");
									} while(resp < 0 || resp > 1);
									
									if(resp == 0)
										condicao = false;
									
								} else
									condicao = false;
							}
						}
					}
				}
			}
		}
		
		System.out.print("\nFim do sistema");
		
		entrada.close();
		
		*/
	}
}