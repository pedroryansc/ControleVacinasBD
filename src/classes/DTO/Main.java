package classes.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.BO.BO;
import transfer.*;

public class Main {
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		
		/*
		
		Lote lote1 = new Lote("210200", "Contra Gripe", "Butantan", usItuporanga);
		Lote lote2 = new Lote("FN9509", "Contra Covid-19", "Pfizer", usItuporanga);
		
		data = LocalDate.parse("2021-06-11");
		
		data = LocalDate.parse("2022-07-27");
		
		*/
		
		// Tela inicial
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		BO bo = new BO();
		
		Cidadao cidadao = null;
		Funcionario func = null;
		Administrador admin = null;
		
		int pers = 1;

		int resp;
		
		boolean resultado = false;
		
		while(pers != 0) {
			
			int opcao = 1;
			
			System.out.println("\nBem-vindo(a) ao Conecte SUS \n");
			
			do {
				System.out.println("Qual mecanismo de persistência você quer utilizar?");
				System.out.println("(1) Banco de Dados (MySQL) \n(2) JSON \n(3) XML \n(0) Encerrar sistema");
				pers = entrada.nextInt();
				if(pers < 0 || pers > 3)
					System.out.println("\nErro: Op��o inv�lida\n");
			} while(pers < 0 || pers > 3);
			
			if(pers > 0) {
				
				while(opcao != 0) {
					
					System.out.print("\nTipo de persistência: ");
					if(pers == 1) {
						System.out.println("Banco de Dados (MySQL) \n");
						bo.setPersistencia(new SQL());
					} else if(pers == 2) {
						System.out.println("JSON \n");
						bo.setPersistencia(new JSON());
					} else {
						System.out.println("XML \n");
						bo.setPersistencia(new XML());
					}
						
					do {
						System.out.println("O que voc� quer fazer?");
						System.out.println("(1) Login \n(0) Voltar");
						opcao = entrada.nextInt();
						if(opcao < 0 || opcao > 1)
							System.out.println("\nErro: Op��o inv�lida\n");
					} while(opcao < 0 || opcao > 1);
					
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
							boolean condicao = true;
							boolean condicao1 = true;
							
							if(resp == 1) {
								
								// Login como cidad�o
								
								System.out.println("\nLogin como Cidad�o: \n");
								
								while(condicao) {
									System.out.println("N�mero de sua Carteira Nacional de Sa�de: ");
									numeroCNS = entrada.nextInt();
									
									cidadao = new Cidadao(numeroCNS);
									
									cidadao = bo.persistencia.getCidadao().procurarId(cidadao);
									
									if(cidadao != null)
										condicao = false;
									else
										System.out.println("\nErro: N�mero de CNS inexistente. Por favor, insira novamente\n");
								}
								
								System.out.println("\nLogin realizado com sucesso!\n");
								
								// Menu do cidad�o
								
								System.out.println("Bem-vindo(a), " + cidadao.getNome());
								
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
										
										RegistroVacina registro = new RegistroVacina(cidadao);
										
										List<RegistroVacina> registros = new ArrayList<RegistroVacina>();
										
										registros = bo.persistencia.getRegistroVacina().procurarTodosPorId(registro);
										
										System.out.println("\nMinhas Vacinas:");
										
										if(!(registros.isEmpty())) {
											for(int i = 0; i < registros.size(); i++) {
												System.out.println("\nData: " + registros.get(i).getData().format(dataFormato));
												System.out.println("Vacina: " + registros.get(i).getLote().getNomeVacina());
												System.out.println("Lote: " + registros.get(i).getLote().getCodigo());
												System.out.println("Laborat�rio: " + registros.get(i).getLote().getLaboratorio());
												System.out.println("Vacinador(a): " + registros.get(i).getVacinador().getNome());
												if(registros.get(i).getDose() != 0)
													System.out.println("Dose: " + registros.get(i).getDose() + "a");
												System.out.println("Unidade de Sa�de: " + registros.get(i).getUnidadeSaude().getNome() + " (" + registros.get(i).getUnidadeSaude().getCidade() + ")");
											}
										} else
											System.out.println("\nNenhum registro de vacina encontrado");
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
									
									func = new Funcionario(numeroCNS, senha);
									
									func = bo.persistencia.getFuncionario().procurarIdSenha(func);
									
									if(func != null)
										condicao = false;
									else
										System.out.println("\nErro: N�mero de CNS e/ou senha inv�lida\n");
								}
								
								System.out.println("\nLogin realizado com sucesso!\n");
								
								// Menu do funcion�rio
								
								System.out.println("Bem-vindo(a), " + func.getNome());
								
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
										
										RegistroVacina registro = new RegistroVacina(func);
										
										List<RegistroVacina> registros = new ArrayList<RegistroVacina>();
										
										registros = bo.persistencia.getRegistroVacina().procurarTodosPorId(registro);
										
										System.out.println("\nMinhas Vacinas:");
										
										if(!(registros.isEmpty())) {
											for(int i = 0; i < registros.size(); i++) {
												System.out.println("\nData: " + registros.get(i).getData().format(dataFormato));
												System.out.println("Vacina: " + registros.get(i).getLote().getNomeVacina());
												System.out.println("Lote: " + registros.get(i).getLote().getCodigo());
												System.out.println("Laborat�rio: " + registros.get(i).getLote().getLaboratorio());
												System.out.println("Vacinador(a): " + registros.get(i).getVacinador().getNome());
												if(registros.get(i).getDose() != 0)
													System.out.println("Dose: " + registros.get(i).getDose() + "a");
												System.out.println("Unidade de Sa�de: " + registros.get(i).getUnidadeSaude().getNome() + " (" + registros.get(i).getUnidadeSaude().getCidade() + ")");
											}
										} else
											System.out.println("\nNenhum registro de vacina encontrado");
										
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
											
											cidadao = new Cidadao(numeroCNS);
											
											cidadao = bo.persistencia.getCidadao().procurarId(cidadao);
											
											if(cidadao != null)
												condicao1 = false;
											else
												System.out.println("\nErro: N�mero de CNS inexistente. Por favor, insira novamente");
										}
										
										System.out.println("\nCidadã(o): " + cidadao.getNome());
										
										RegistroVacina registro = new RegistroVacina(cidadao);
										
										List<RegistroVacina> registros = new ArrayList<RegistroVacina>();
										
										registros = bo.persistencia.getRegistroVacina().procurarTodosPorId(registro);
										
										if(!(registros.isEmpty())) {
											for(int i = 0; i < registros.size(); i++) {
												System.out.println("\nData: " + registros.get(i).getData().format(dataFormato));
												System.out.println("Vacina: " + registros.get(i).getLote().getNomeVacina());
												System.out.println("Lote: " + registros.get(i).getLote().getCodigo());
												System.out.println("Laborat�rio: " + registros.get(i).getLote().getLaboratorio());
												System.out.println("Vacinador(a): " + registros.get(i).getVacinador().getNome());
												if(registros.get(i).getDose() != 0)
													System.out.println("Dose: " + registros.get(i).getDose() + "a");
												System.out.println("Unidade de Sa�de: " + registros.get(i).getUnidadeSaude().getNome() + " (" + registros.get(i).getUnidadeSaude().getCidade() + ")");
											}
										} else
											System.out.println("\nNenhum registro de vacina encontrado.");
										
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
										
										Lote lote = new Lote(func.getUnidadeSaude());
										
										List<Lote> lotes = new ArrayList<Lote>();
										
										lotes = bo.persistencia.getLote().procurarTodosPorIdUS(lote);
										
										if(!(lotes.isEmpty())) {
											
											// Cadastro de registro de vacina
											
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
											
											int loteCodigo;
											
											do {
												System.out.println("\nLote:");
												for(int i = 0; i < lotes.size(); i++) {
														System.out.println("(" + (i + 1) + ") " + lotes.get(i).getNomeVacina() + " (" + lotes.get(i).getCodigo() + ")");
												}
												loteCodigo = entrada.nextInt();
												if(loteCodigo < 1 || loteCodigo > lotes.size())
													System.out.println("\nErro: Op��o inv�lida");
											} while(loteCodigo < 1 || loteCodigo > lotes.size());
											
											loteCodigo--;
											
											cidadao = null;
											
											while(cidadao == null) {
												System.out.println("\nN�mero da Carteira Nacional de Sa�de do Cidad�o:");
												numeroCNS = entrada.nextInt();
												
												cidadao = new Cidadao(numeroCNS);
												
												cidadao = bo.persistencia.getCidadao().procurarId(cidadao);
												
												if(cidadao == null)
													System.out.println("\nErro: N�mero de CNS inexistente. Por favor, insira novamente");
											}
											
											Funcionario vac = new Funcionario(func.getUnidadeSaude());
											
											List<Funcionario> vacinadores = new ArrayList<Funcionario>();
											
											vacinadores = bo.persistencia.getFuncionario().procurarTodosPorIdUS(vac);
											
											int vacinador;
											
											do {
												System.out.println("\nVacinador(a):");
												for(int i = 0; i < vacinadores.size(); i++)
													System.out.println("(" + (i + 1) + ") " + vacinadores.get(i).getNome());
												vacinador = entrada.nextInt();
												if(vacinador < 1 || vacinador > vacinadores.size())
													System.out.println("\nErro: Op��o inv�lida");
											} while(vacinador < 1 || vacinador > vacinadores.size());
											
											vacinador--;
											
											int dose;
											
											do {
												System.out.println("\nDose (ou digite 0 se n�o for o caso):");
												dose = entrada.nextInt();
												if(dose < 0)
													System.out.println("\nErro: Valor inv�lido");
											} while(dose < 0);
											
											LocalDate data = LocalDate.parse(dataVetor[2] + "-" + dataVetor[1] + "-" + dataVetor[0]);
											RegistroVacina registro = new RegistroVacina(0, data, lotes.get(loteCodigo), cidadao, vacinadores.get(vacinador), dose, func.getUnidadeSaude());
											
											resultado = bo.persistencia.getRegistroVacina().inserir(registro);
											
											if(resultado)
												System.out.println("\nCadastro realizado com sucesso!");
											else
												System.out.println("\nErro ao inserir.");
											
											do {
												System.out.println("\nO que voc� quer fazer?");
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
										
										Lote lote = new Lote(func.getUnidadeSaude());
										
										List<Lote> lotes = new ArrayList<Lote>();
										
										lotes = bo.persistencia.getLote().procurarTodosPorIdUS(lote);
										
										System.out.println("\nLotes de Vacina de " + func.getUnidadeSaude().getNome());
										
										if(!(lotes.isEmpty())) {
											for(int i = 0; i < lotes.size(); i++) {
												System.out.println("\nC�digo: " + lotes.get(i).getCodigo());
												System.out.println("Vacina: " + lotes.get(i).getNomeVacina());
												System.out.println("Laborat�rio: " + lotes.get(i).getLaboratorio());
											}
										} else
											System.out.println("\nNenhum lote encontrado.");
										
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
										
										System.out.println("Código:");
										String codigo = entrada.nextLine();
										
										System.out.println("\nVacina:");
										String vacina = entrada.nextLine();
										
										System.out.println("\nLaborat�rio:");
										String laboratorio = entrada.nextLine();
										
										Lote lote = new Lote(codigo, vacina, laboratorio, func.getUnidadeSaude());
										
										resultado = bo.persistencia.getLote().inserir(lote);
										
										if(resultado)
											System.out.println("\nCadastro realizado com sucesso!");
										else
											System.out.println("\nErro ao inserir.");
										
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
									
									admin = new Administrador(numeroCNS, senha);
									
									admin = bo.persistencia.getAdministrador().procurarIdSenha(admin);
									
									if(admin != null)
										condicao = false;
									else
										System.out.println("\nErro: N�mero de CNS e/ou senha inv�lida\n");
								}
								
								System.out.println("\nLogin realizado com sucesso!\n");
								
								// Menu do administrador
								
								System.out.println("Bem-vindo(a), " + admin.getNome());
								
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
										
										List<UnidadeSaude> unidades = new ArrayList<UnidadeSaude>();
										
										unidades = bo.persistencia.getUnidadeSaude().procurarTodas();
										
										System.out.println("\nUnidades de Sa�de");
										
										for(int i = 0; i < unidades.size(); i++) {
											System.out.println("\nNome: " + unidades.get(i).getNome());
											System.out.println("Endereço: " + unidades.get(i).getRua() + ", " + unidades.get(i).getBairro() + " - " + unidades.get(i).getCidade() + " (" + unidades.get(i).getEstado() + ")");
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
										
										UnidadeSaude us = new UnidadeSaude(0, nomeUnidade, rua, bairro, cidade, estado, telefone);
										
										resultado = bo.persistencia.getUnidadeSaude().inserir(us);
										
										if(resultado)
											System.out.println("\nCadastro realizado com sucesso!");
										else
											System.out.println("\nErro ao inserir.");
										
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
		}
		
		System.out.print("\nFim do sistema");
		
		entrada.close();
	}
}