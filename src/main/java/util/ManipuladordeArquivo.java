package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exception.EmpresaNaoCadastradaException;
import model.OrdemServico;

public class ManipuladordeArquivo {

	private static final String CAMINHO_ORDENS = FacesContext.getCurrentInstance().getExternalContext()
			.getRealPath("/WEB-INF/ordem-servico/ordens.");

	@SuppressWarnings({ "unused", "resource" })
	public static List<OrdemServico> gravarArquivo(byte[] arquivo, String extensao)
			throws IOException, EmpresaNaoCadastradaException {

		String caminho = CAMINHO_ORDENS + extensao;

		List<OrdemServico> listaOrdens = new ArrayList<OrdemServico>();

		System.out.println(caminho);

		if (!new File(caminho).exists()) {
			String caminhoPasta = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/WEB-INF/colaboradores-fortes");
			new File(caminhoPasta).mkdirs();
		}

		BufferedWriter StrW = new BufferedWriter(new FileWriter(caminho));
		byte[] arquivoAux = arquivo;
		FileOutputStream fos = new FileOutputStream(caminho);
		fos.write(arquivoAux);
		fos.close();

		listaOrdens = lerArquivoOrdens(caminho);

		return listaOrdens;

	}

	public static List<OrdemServico> lerArquivoOrdens(String path) throws EmpresaNaoCadastradaException {

		List<OrdemServico> listaOrdem = new ArrayList<OrdemServico>();
		OrdemServico ordemServico;
		boolean item = false;

		try {

			FileInputStream file = new FileInputStream(new File(path));

			Workbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				ordemServico = new OrdemServico();
				item = false;
			
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					// Linhas ignoradas
					if (row.getRowNum() == 0) {
						break;
					}

					
					switch (cell.getColumnIndex()) {
					case 0:

						if (String.valueOf(cell.getNumericCellValue()).isEmpty()) {
							item = true;
							break;
						} 
						
						ordemServico.setNumeroOrdemServico(String.valueOf(cell.getNumericCellValue()));
						break;
					case 2:

						if (String.valueOf(cell.getDateCellValue()).isEmpty()) {
							break;
						}

						ordemServico.setDataAbertura(cell.getDateCellValue());
						break;
					case 17:

						if (cell.getStringCellValue().isEmpty()) {
							break;
						}

//						ordemServico.setReferencia(cell.getStringCellValue());
						break;	

					}
					

				}
				System.out.println(ordemServico.getNumeroOrdemServico() + " - " + ordemServico.getDataAbertura());
				listaOrdem.add(ordemServico);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listaOrdem;
	}

	public static String getExtensao(String nomeArquivo) {
		if (nomeArquivo.isEmpty() || nomeArquivo == null)
			return null;
		return nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length()).toLowerCase();
	}

}
