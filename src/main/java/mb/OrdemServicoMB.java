package mb;

import static util.Message.addErrorMessage;
import static util.Message.addInfoMessage;
import static util.Message.addWarnMessage;
import static util.Message.setErrorMessage;
import static util.Message.setInfoMessage;
import static util.Message.setWarnMessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import bo.ItemOrdemServicoBO;
import bo.OrdemServicoBO;
import bo.ReceitaOrdemServicoBO;
import br.hibernateutil.exception.IntegridadeObjetoHibernateException;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import constants.EtapaOrdemServico;
import constants.StatusOrdemServico;
import constants.TipoOrdemServico;
import exception.EmpresaNaoCadastradaException;
import exception.ObjetoExistenteException;
import model.ItemOrdemServico;
import model.OrdemServico;
import model.ReceitaOrdemServico;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.ManipuladordeArquivo;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class OrdemServicoMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;
	private final String CAD = "/private/cadastro/cadastrarOrdemServico.xhtml?faces-redirect=true";
	private final String EDIT = "/private/cadastro/cadastrarOrdemServico.xhtml";
	private final String LIST = "/private/lista/listarOrdemServico.xhtml?faces-redirect=true";

	private OrdemServico ordemServico;
	private OrdemServico ordemServicoFilter;
	private LazyEntityDataModel<OrdemServico> lazy;
	private List<OrdemServico> ordemServicos;

	private ItemOrdemServico itemOrdemServico;
	private List<ItemOrdemServico> itemOrdemServicoList;
	private List<ItemOrdemServico> itemOrdemServicoListAux;
	
	private ReceitaOrdemServico receitaOrdemServico;
	private List<ReceitaOrdemServico> receitaOrdemServicoList;
	private List<ReceitaOrdemServico> receitaOrdemServicoListAux;

	@PostConstruct
	public void init() {

		OrdemServico aux = RecuperarObjetoViaRequisicao.getObjeto(OrdemServico.class, "id");
		ordemServico = aux != null ? aux : new OrdemServico();

		ordemServicoFilter = new OrdemServico();
		lazy = new LazyEntityDataModel<OrdemServico>(OrdemServico.class);
		ordemServicos = new ArrayList<OrdemServico>();

		itemOrdemServicoList = new ArrayList<ItemOrdemServico>();
		itemOrdemServicoListAux = new ArrayList<ItemOrdemServico>();
		
		receitaOrdemServico = new ReceitaOrdemServico();
		receitaOrdemServicoList = new ArrayList<ReceitaOrdemServico>();
		receitaOrdemServicoListAux = new ArrayList<ReceitaOrdemServico>();

		// CASO SEJA EDI��O DE DADOS
		if (ordemServico.getId() != null) {
			try {
				itemOrdemServicoList = ItemOrdemServicoBO.getInstance()
						.itemOrdemServicoMetaLojaPorOrdemServico(ordemServico);
				receitaOrdemServicoList = ReceitaOrdemServicoBO.getInstance()
						.receitaOrdemServicoMetaLojaPorOrdemServico(ordemServico);
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
	}

	public String save() {
		try {
			ordemServico.setItensOrdemServico(itemOrdemServicoList);
			ordemServico.setReceitasOrdemServico(receitaOrdemServicoList);
			
			OrdemServicoBO.save(ordemServico);

			for (int i = 0; i < itemOrdemServicoList.size(); i++) {
				itemOrdemServicoList.get(i).setOrdemServico(ordemServico);
			}

			if (!itemOrdemServicoList.isEmpty()) {
				ItemOrdemServicoBO.getInstance().mergeAll(itemOrdemServicoList);
			}

			for (int i = 0; i < receitaOrdemServicoList.size(); i++) {
				receitaOrdemServicoList.get(i).setOrdemServico(ordemServico);
			}
			
			if (!receitaOrdemServicoList.isEmpty()) {
				ReceitaOrdemServicoBO.getInstance().mergeAll(receitaOrdemServicoList);
			}
			setInfoMessage("Cadastrado com sucesso!", "OrdemServico " + ordemServico.getDataAbertura());
		} catch (ViolacaoChaveHibernateException e) {
			addErrorMessage("Formulario", "erro.salvar.entidade.campo.existente", "");
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ObjetoExistenteException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ListaVaziaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}
		ordemServico = new OrdemServico();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String update() {

		try {
			ordemServico.setItensOrdemServico(itemOrdemServicoList);
			ordemServico.setReceitasOrdemServico(receitaOrdemServicoList);

			OrdemServicoBO.update(ordemServico);

			for (int i = 0; i < itemOrdemServicoList.size(); i++) {
				itemOrdemServicoList.get(i).setOrdemServico(ordemServico);
			}

			if (!itemOrdemServicoList.isEmpty()) {
				ItemOrdemServicoBO.getInstance().mergeAll(itemOrdemServicoList);
			}

			if (!itemOrdemServicoListAux.isEmpty()) {
				ItemOrdemServicoBO.getInstance().deleteAll(itemOrdemServicoListAux);
			}

			for (int i = 0; i < receitaOrdemServicoList.size(); i++) {
				receitaOrdemServicoList.get(i).setOrdemServico(ordemServico);
			}
			
			if (!receitaOrdemServicoList.isEmpty()) {
				ReceitaOrdemServicoBO.getInstance().mergeAll(receitaOrdemServicoList);
			}
			
			if (!receitaOrdemServicoListAux.isEmpty()) {
				ReceitaOrdemServicoBO.getInstance().deleteAll(receitaOrdemServicoListAux);
			}

			setInfoMessage("Editado com sucesso!", "OrdemServico " + ordemServico.getDataAbertura());
			
		} catch (ViolacaoChaveHibernateException e) {
			addErrorMessage("Formulario", "erro.salvar.entidade.campo.existente", "");
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ObjetoExistenteException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (IntegridadeObjetoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ListaVaziaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}
		ordemServico = new OrdemServico();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	/* ENUMS */
	public TipoOrdemServico[] tiposOrdens() {
		return TipoOrdemServico.values();
	}

	public StatusOrdemServico[] statusOrdens() {
		return StatusOrdemServico.values();
	}

	public EtapaOrdemServico[] etapasOrdens() {
		return EtapaOrdemServico.values();
	}

	public void clearItemOrdemServico() {
		itemOrdemServico = new ItemOrdemServico();
	}

	public void clearReceitaOrdemServico() {
		receitaOrdemServico = new ReceitaOrdemServico();
	}
	
//	public void setarValorUnitario() {
//		itemOrdemServico.setValorUnitario(itemOrdemServico.getProduto().);
//	}
	public String removerItemOrdemServico() {
		try {
			for (int i = 0; i < itemOrdemServicoList.size(); i++) {
				if (itemOrdemServicoList.get(i) == itemOrdemServico) {
					itemOrdemServicoListAux.add(itemOrdemServicoList.get(i));
					itemOrdemServicoList.remove(i);
					addWarnMessage("Item removido com sucesso!", "");
					break;
				}
			}
			itemOrdemServico = new ItemOrdemServico();
		} catch (Exception e) {
			addErrorMessage("", e.getMessage());
		}
		return null;
	}

	public String addItemOperacaoMetaLoja() {
		try {
			itemOrdemServicoList.add(itemOrdemServico);
			addInfoMessage("Item adicionada com sucesso!", "");
			itemOrdemServico = new ItemOrdemServico();
		} catch (Exception e) {
			addWarnMessage("", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String updateItemOperacaoMetaLoja() {
		try {
			for (int i = 0; i < itemOrdemServicoList.size(); i++) {
				if (itemOrdemServicoList.get(i) == itemOrdemServico) {
					itemOrdemServicoList.set(i, itemOrdemServico);
					addInfoMessage("Item atualizada com sucesso!", "");
					break;
				}
			}
			itemOrdemServico = new ItemOrdemServico();
		} catch (Exception e) {
			addErrorMessage("", e.getMessage());
		}
		return null;
	}
	
	public String removerReceitaOrdemServico() {
		try {
			for (int i = 0; i < receitaOrdemServicoList.size(); i++) {
				if (receitaOrdemServicoList.get(i) == receitaOrdemServico) {
					receitaOrdemServicoListAux.add(receitaOrdemServicoList.get(i));
					receitaOrdemServicoList.remove(i);
					addWarnMessage("Item removido com sucesso!", "");
					break;
				}
			}
			receitaOrdemServico = new ReceitaOrdemServico();
		} catch (Exception e) {
			addErrorMessage("", e.getMessage());
		}
		return null;
	}
	
	public String addReceitaOperacaoMetaLoja() {
		try {
			receitaOrdemServicoList.add(receitaOrdemServico);
			addInfoMessage("Item adicionada com sucesso!", "");
			receitaOrdemServico = new ReceitaOrdemServico();
		} catch (Exception e) {
			addWarnMessage("", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateReceitaOperacaoMetaLoja() {
		try {
			for (int i = 0; i < receitaOrdemServicoList.size(); i++) {
				if (receitaOrdemServicoList.get(i) == receitaOrdemServico) {
					receitaOrdemServicoList.set(i, receitaOrdemServico);
					addInfoMessage("Item atualizada com sucesso!", "");
					break;
				}
			}
			receitaOrdemServico = new ReceitaOrdemServico();
		} catch (Exception e) {
			addErrorMessage("", e.getMessage());
		}
		return null;
	}

	public void gerarPDF(ActionEvent ev) {
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();

			List<OrdemServico> ordemServicos = OrdemServicoBO.ordemServicos(ordemServicoFilter);

			ordemServicoFilter = new OrdemServico();
			// mapa.put("logo",
			// BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
			// mapa.put("rodape",
			// BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
			// mapa.put("filtro",OrdemServicoBO.dadosFiltro());

			GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, ordemServicos, "ordemServicos",
					"Relatório de ordemServicos " + DataUtil.formatarData(new Date()), true);

		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

	public String list() {

		lazy = OrdemServicoBO.ordemServicosLazy(ordemServicoFilter);

		return null;
	}

	public List<OrdemServico> getOrdemServicoAutoComplete(String nome) {

		List<OrdemServico> categoriaComplete = new ArrayList<OrdemServico>();
		try {
			categoriaComplete = OrdemServicoBO.ordemServicosComplete(nome);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return categoriaComplete;
	}

	public String goToList() {
		return LIST;
	}

	public String prepareUpdate() {
		return EDIT;
	}

	public String prepareSave() {
		ordemServico = new OrdemServico();
		return CAD;
	}

	public String updateStatus() {
		try {

			if (ordemServico.getStatus()) {
				ordemServico.setStatus(false);
				OrdemServicoBO.update(ordemServico);
			} else {
				ordemServico.setStatus(true);
				OrdemServicoBO.update(ordemServico);
			}

		} catch (ViolacaoChaveHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ObjetoExistenteException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}

		String status = ordemServico.getStatus() ? "Ativo" : "Inativo";

		setInfoMessage("Status alterado com sucesso!", "O ordemServico está " + status);
		return null;
	}

	public void carregarArquivo(FileUploadEvent event) {

		try {
			byte[] arquivo = event.getFile().getContents();
			List<OrdemServico> listaAux = new ArrayList<>();

			listaAux = ManipuladordeArquivo.gravarArquivo(arquivo,
					ManipuladordeArquivo.getExtensao(event.getFile().getFileName()));

			// for (Colaborador c : listaAux) {
			//
			// c.setLoja(lojaSelecionada);
			//
			// if (!c.getCodigoFortes().isEmpty()
			// && ColaboradorBO.verificarColaboradorImportacao(c.getLoja(),
			// c.getCodigoFortes()).isEmpty()) {
			// colaboradors.add(c);
			// }
			//
			// }

			System.out.print(listaAux);

		} catch (FileNotFoundException e) {
			setWarnMessage("Atenção!", "Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EmpresaNaoCadastradaException e) {
			setWarnMessage("Atenção!", "Empresa não cadastrada!");
			e.printStackTrace();
			// } catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			// e.printStackTrace();
		}

	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public LazyEntityDataModel<OrdemServico> getLazy() {
		return lazy;
	}

	public void setLazy(LazyEntityDataModel<OrdemServico> lazy) {
		this.lazy = lazy;
	}

	public List<OrdemServico> getOrdemServicos() {
		try {
			ordemServicos = OrdemServicoBO.list();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public OrdemServico getOrdemServicoFilter() {
		return ordemServicoFilter;
	}

	public void setOrdemServicoFilter(OrdemServico ordemServicoFilter) {
		this.ordemServicoFilter = ordemServicoFilter;
	}

	public String getCAD() {
		return CAD;
	}

	public String getEDIT() {
		return EDIT;
	}

	public String getLIST() {
		return LIST;
	}

	public ItemOrdemServico getItemOrdemServico() {
		return itemOrdemServico;
	}

	public void setItemOrdemServico(ItemOrdemServico itemOrdemServico) {
		this.itemOrdemServico = itemOrdemServico;
	}

	public List<ItemOrdemServico> getItemOrdemServicoList() {
		return itemOrdemServicoList;
	}

	public void setItemOrdemServicoList(List<ItemOrdemServico> itemOrdemServicoList) {
		this.itemOrdemServicoList = itemOrdemServicoList;
	}

	public List<ItemOrdemServico> getItemOrdemServicoListAux() {
		return itemOrdemServicoListAux;
	}

	public void setItemOrdemServicoListAux(List<ItemOrdemServico> itemOrdemServicoListAux) {
		this.itemOrdemServicoListAux = itemOrdemServicoListAux;
	}

	public ReceitaOrdemServico getReceitaOrdemServico() {
		return receitaOrdemServico;
	}

	public void setReceitaOrdemServico(ReceitaOrdemServico receitaOrdemServico) {
		this.receitaOrdemServico = receitaOrdemServico;
	}

	public List<ReceitaOrdemServico> getReceitaOrdemServicoList() {
		return receitaOrdemServicoList;
	}

	public void setReceitaOrdemServicoList(List<ReceitaOrdemServico> receitaOrdemServicoList) {
		this.receitaOrdemServicoList = receitaOrdemServicoList;
	}

	public List<ReceitaOrdemServico> getReceitaOrdemServicoListAux() {
		return receitaOrdemServicoListAux;
	}

	public void setReceitaOrdemServicoListAux(List<ReceitaOrdemServico> receitaOrdemServicoListAux) {
		this.receitaOrdemServicoListAux = receitaOrdemServicoListAux;
	}
	
	

}
