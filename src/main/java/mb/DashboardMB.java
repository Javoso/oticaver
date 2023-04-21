package mb;

import static util.Message.addInfoMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import acesso.EscopoSessaoBean;
import bo.ColaboradorBO;
import bo.LojaBO;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import model.Loja;
import model.Usuario;
import util.AES;
import util.ManagedBeanHelper;
import util.RecuperaMensagemComFlash;

@ManagedBean
@ViewScoped
public class DashboardMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;

	private PieChartModel pieModel;
	private HorizontalBarChartModel hbarModel;
	private LineChartModel lineModel;
	private int quantidadeDeMetas = 0;
	private Usuario usuarioLogado;
	private Loja lojaSelecionada; 

	@PostConstruct
	public void init() {
		usuarioLogado = ManagedBeanHelper.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class).getUsuarioLogado();
		createPieModel();
		createHorizontalBarModel();
		createLineModel();
		initQuantidade();
	}
	
	private void initQuantidade() {
//		if (usuarioLogado != null) {
//			try {
//				if (usuarioLogado.isAdministrador() || usuarioLogado.isDpFinanceiro() || usuarioLogado.isDpPessoal())
//					quantidadeDeMetas = ControleMetaLojaBO.getInstance().countDeMetasParaValidar();
//
//				if (usuarioLogado.isGerente())
//					quantidadeDeMetas = ControleMetaLojaBO.getInstance().countDeMetasParaDistribuir(usuarioLogado);
//
//			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public String linkDistribuicoesParaValidar() {
		return "/private/lista/listarControleMetaLoja.xhtml?faces-redirect=true";
	}

	public String paramCodificado(String numero) {
		return new AES().codificar(numero);
	}

	public int getTotalLojas() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
				return LojaBO.list().size();
	}
	
	public String selecionarLoja() {	
		usuarioLogado.setLoja(lojaSelecionada);
		ManagedBeanHelper.recuperaBean("escopoSessaoBean",
				EscopoSessaoBean.class).setUsuarioLogado(usuarioLogado);
		addInfoMessage("Loja selecionada! " + usuarioLogado.getLoja().getNome(), "");
		RecuperaMensagemComFlash.flashScope().setKeepMessages(true);
		return "/private/dashboard/dashboard.xhtml?faces-redirect=true";
	}
	
	public int getTotalColaboradores() {
		try {
			if (usuarioLogado.notGerente()) {
				return ColaboradorBO.list().size();
			} else {
				return ColaboradorBO.colaboradoresPorLoja(usuarioLogado.getLoja()).size();
			}
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			throw new RuntimeException(e);
		}
	}

	private void createPieModel() {
		pieModel = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();
		List<String> labels = new ArrayList<>();

		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(54, 162, 235)");
		bgColors.add("rgb(255, 205, 86)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);

		initListValuesCharts(values, labels);

		dataSet.setData(values);
		data.setLabels(labels);

		pieModel.setData(data);
	}

	private void initListValuesCharts(List<Number> values, List<String> labels) {
		if (usuarioLogado.notGerente()) {
			try {
				List<Loja> lojas = LojaBO.list();

				lojas.forEach(l -> {
					labels.add(l.getNome());
						values.add(10);
//						values.add(ColaboradorBO.colaboradoresPorLoja(l).size());
				});
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				throw new RuntimeException(e);
			}
		} 
	}

	public void createHorizontalBarModel() {
		hbarModel = new HorizontalBarChartModel();
		ChartData data = new ChartData();

		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
		hbarDataSet.setLabel("Quantidade");

		List<Number> values = new ArrayList<>();

		List<String> bgColor = new ArrayList<>();
		bgColor.add("rgba(255, 99, 132, 0.2)");
		bgColor.add("rgba(255, 159, 64, 0.2)");
		bgColor.add("rgba(255, 205, 86, 0.2)");
		bgColor.add("rgba(75, 192, 192, 0.2)");
		bgColor.add("rgba(54, 162, 235, 0.2)");
		// bgColor.add("rgba(153, 102, 255, 0.2)");
		// bgColor.add("rgba(201, 203, 207, 0.2)");
		hbarDataSet.setBackgroundColor(bgColor);

		List<String> borderColor = new ArrayList<>();
		borderColor.add("rgb(255, 99, 132)");
		borderColor.add("rgb(255, 159, 64)");
		borderColor.add("rgb(255, 205, 86)");
		borderColor.add("rgb(75, 192, 192)");
		borderColor.add("rgb(54, 162, 235)");
		// borderColor.add("rgb(153, 102, 255)");
		// borderColor.add("rgb(201, 203, 207)");
		hbarDataSet.setBorderColor(borderColor);
		hbarDataSet.setBorderWidth(1);

		data.addChartDataSet(hbarDataSet);

		List<String> labels = new ArrayList<>();

		initListValuesCharts(values, labels);

		data.setLabels(labels);
		hbarDataSet.setData(values);

		hbarModel.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		linearAxes.setOffset(true);
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		linearAxes.setTicks(ticks);
		cScales.addXAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Colaboradores por loja");
		options.setTitle(title);

		hbarModel.setOptions(options);
	}
	
	public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Contas a receber");
        dataSet.setBorderColor("rgb(0, 128, 0)");
        dataSet.setLineTension(0.1);

        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Number> values2 = new ArrayList<>();
        values2.add(30);
        values2.add(40);
        values2.add(65);
        values2.add(90);
        values2.add(35);
        values2.add(85);
        values2.add(70);
        dataSet2.setData(values2);
        dataSet2.setFill(false);
        dataSet2.setLabel("Contas a pagar");
        dataSet2.setBorderColor("rgb(255, 160, 122)");
        dataSet2.setLineTension(0.1);
        
        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);
        
        

        List<String> labels = new ArrayList<>();
        labels.add("Janeiro");
        labels.add("Fevereiro");
        labels.add("Março");
        labels.add("Abril");
        labels.add("Maio");
        labels.add("Junho");
        labels.add("Julho");
        labels.add("Agosto");
        labels.add("Setembro");
        labels.add("Outubro");
        labels.add("Novembro");
        labels.add("Dezembro");
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Análise de contas a pagar e contas a receber");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public HorizontalBarChartModel getHbarModel() {
		return hbarModel;
	}

	public void setHbarModel(HorizontalBarChartModel hbarModel) {
		this.hbarModel = hbarModel;
	}

	public int getQuantidadeDeMetas() {
		return quantidadeDeMetas;
	}

	public void setQuantidadeDeMetas(int quantidadeDeMetas) {
		this.quantidadeDeMetas = quantidadeDeMetas;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}
	
	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}
	
	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

}
