package constants;

public enum Estados {

	 /**
     * Nome: Acre, Sigla: AC
     */
    ACRE("Acre", "AC"),
    /**
     * Nome: Alagoas, Sigla: AL
     */
    ALAGOAS("Alagoas", "AL"),
    /**
     * Nome: Amap�, Sigla: AP
     */
    AMAPA("Amap�", "AP"),
    /**
     * Nome: Amazonas, Sigla: AM
     */
    AMAZONAS("Amazonas", "AM"),
    /**
     * Nome: Bahia, Sigla: BA
     */
    BAHIA("Bahia", "BA"),
    /**
     * Nome: Cear�, Sigla: CE
     */
    CEARA("Cear�", "CE"),
    /**
     * Nome: Distrito Federal, Sigla: DF
     */
    DISTRITOFEDERAL("Distrito Federal", "DF"),
    /**
     * Nome: Esp�rito Santo, Sigla: ES
     */
    ESPIRITOSANTO("Esp�rito Santo", "ES"),
    /**
     * Nome: Goi�s, Sigla: GO
     */
    GOIAS("Goi�s", "GO"),
    /**
     * Nome: Maranh�o, Sigla: MA
     */
    MARANHAO("Maranh�o", "MA"),
    /**
     * Nome: Mato Grosso, Sigla: MT
     */
    MATOGROSSO("Mato Grosso", "MT"),
    /**
     * Nome: Mato Grosso do Sul, Sigla: MS
     */
    MATOGROSSODOSUL("Mato Grosso do Sul", "MS"),
    /**
     * Nome: Minas Gerais, Sigla: MG
     */
    MINASGERAIS("Minas Gerais", "MG"),
    /**
     * Nome: Par�, Sigla: PA
     */
    PARA("Par�", "PA"),
    /**
     * Nome: Para�ba, Sigla: PB
     */
    PARAIBA("Para�ba", "PB"),
    /**
     * Nome: Paran�, Sigla: PR
     */
    PARANA("Paran�", "PR"),
    /**
     * Nome: Pernambuco, Sigla: PE
     */
    PERNAMBUCO("Pernambuco", "PE"),
    /**
     * Nome: Piau�, Sigla: PI
     */
    PIAUI("Piau�", "PI"),
    /**
     * Nome: Rio de Janeiro, Sigla: RJ
     */
    RIODEJANEIRO("Rio de Janeiro", "RJ"),
    /**
     * Nome: Rio Grande do Norte, Sigla: RN
     */
    RIOGRANDEDONORTE("Rio Grande do Norte", "RN"),
    /**
     * Nome: Rio Grande do Sul, Sigla: RS
     */
    RIOGRANDEDOSUL("Rio Grande do Sul", "RS"),
    /**
     * Nome Rond�nia, Silga: RO
     */
    RONDONIA("Rond�nia", "RO"),
    /**
     * Nome: Roraima, Sigla: RO
     */
    RORAIMA("Roraima", "RR"),
    /**
     * Nome: Santa Catarina, Sigla: SC
     */
    SANTACATARINA("Santa Catarina", "SC"),
    /**
     * Nome: S�o Paulo, Sigla: SP
     */
    SAOPAULO("S�o Paulo", "SP"),
    /**
     * Nome: Sergipe, Sigla: SE
     */
    SERGIPE("Sergipe", "SE"),
    /**
     * Nome: Tocantins, Sigla: TO
     */
    TOCANTINS("Tocantins", "TO");
    private String nome;
    private String sigla;
    
    /**
     * Construtor que recebe nome sigla
     *
     * @param nome
     * @param sigla
     */
    private Estados(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;       
    }

    /**
     * Retorna nome
     * 
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna sigla
     *
     * @return sigla
     */
    public String getSigla() {
        return sigla;
    }

}
