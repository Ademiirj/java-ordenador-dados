package negocio;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import gerador_arquivo.GerarArquivo;
import algoritmos.HeapSort;
import algoritmos.MergeSort;
import algoritmos.QuickSort;
import java.util.ArrayList;

public class ProcessoOrdenacao {

    private int qtdCoord;
    private String nomeArquivo;
    private static final String PATHORDENADO = "src/main/java/ARQUIVOS-GERADOS/";
    private static final String PATHDESORDENADO = "src/main/java/ARQUIVOS-GERADOS/";

    public ProcessoOrdenacao(String inputNomeArquivo, int inputTamanhoArquivo) {
        setNomeArquivo(inputNomeArquivo);
        setQtdCoord(inputTamanhoArquivo);
    }

    public Long[] iniciar() {
        File pastas = new File(PATHORDENADO);
        pastas.mkdirs();
        File diretorio = new File(PATHDESORDENADO);
        diretorio.mkdirs();

        GerarArquivo geradorArquivoDesordenado = new GerarArquivo(PATHDESORDENADO);
        GerarArquivo geradorArquivoOrdenado = new GerarArquivo(PATHORDENADO);

        GerarCoordenada geradorCoordenada = new GerarCoordenada(getQtdCoord());

        geradorCoordenada.gerarCoordenadas();
        List<Double> coordenadasGeradas = geradorCoordenada.getCoordenadas();

        geradorArquivoDesordenado.gerarArquivoCoordenadaDesordenada(coordenadasGeradas, getNomeArquivo());

        double[] arr = listaParaArray(coordenadasGeradas);
        double[] arrQuick = arr.clone();
        double[] arrMerge = arr.clone();
        double[] arrHeap = arr.clone();

        QuickSort quickSort = new QuickSort(arrQuick);
        MergeSort mergeSort = new MergeSort(arrMerge);
        HeapSort heapSort = new HeapSort(arrHeap);

        long tempoExecQuickSort = quickSort.calcularTempoAlgoritmo();
        long tempoExecMergeSort = mergeSort.calcularTempoAlgoritmo();
        long tempoExecHeapSort = heapSort.calcularTempoAlgoritmo();

        geradorArquivoOrdenado.gerarArquivoOrdenado(arrQuick, getNomeArquivo() + "QuickSort");
        geradorArquivoOrdenado.gerarArquivoOrdenado(arrMerge, getNomeArquivo() + "MergeSort");
        geradorArquivoOrdenado.gerarArquivoOrdenado(arrHeap, getNomeArquivo() + "HeapSort");

        Long[] resultado = {tempoExecQuickSort, tempoExecMergeSort, tempoExecHeapSort};
        
        return resultado;
	}

	private String getNomeArquivo() {
        return nomeArquivo;
    }

    private static double[] listaParaArray(List<Double> coordenadasGeradas) {
        Double[] arrDouble = coordenadasGeradas.toArray(new Double[coordenadasGeradas.size()]);
        return ArrayUtils.toPrimitive(arrDouble);
    }

    private int getQtdCoord() {
        return qtdCoord;
    }

    private void setQtdCoord(int inputTamanhoArquivo) {
        qtdCoord = inputTamanhoArquivo;
    }

    private void setNomeArquivo(String inputNomeArquivo) {
        nomeArquivo = inputNomeArquivo;
    }
}
