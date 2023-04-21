package util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.shaded.commons.io.FilenameUtils;

public class ManipuladorArquivoFortes implements Serializable {

	private static final long serialVersionUID = 3273883814032118271L;

	public static void lerArquivo() {

	}

	public static String escritaArquivo(String nome, String textoQueVaiSerEscrito) throws IOException {
		FileWriter fwArquivo;
		BufferedWriter bwArquivo;
		try {
			File arquivo = new File(nome);

			fwArquivo = new FileWriter(arquivo, arquivo.exists());
			bwArquivo = new BufferedWriter(fwArquivo);

			bwArquivo.write(textoQueVaiSerEscrito);

			bwArquivo.close();
			fwArquivo.close();

			return arquivo.getAbsolutePath();

		} catch (IOException e) {
			throw new IOException("Erro ao tentar escrever no arquivo: " + e.toString());
		}
	}

	public static StreamedContent getArquivo(String path, String nomeArquivo) {
		if (isNotBlank(path) && isNotBlank(nomeArquivo)) {
			byte[] bytes;
			try {
				bytes = Files.readAllBytes(Paths.get(path));
				InputStream stream = new ByteArrayInputStream(bytes);
				return new DefaultStreamedContent(stream, FilenameUtils.getExtension(path), nomeArquivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new DefaultStreamedContent();
	}

	public static void downloadArquivo() {

	}

	public static void deletarArquivo(String path) throws IOException {
		if (isNotBlank(path)) {
			File file = new File(path);
			file.delete();
		}

	}
}
