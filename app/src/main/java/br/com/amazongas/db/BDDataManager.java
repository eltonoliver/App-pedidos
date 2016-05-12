package br.com.amazongas.db;

import java.util.ArrayList;
import java.util.List;

import br.com.amazongas.model.Bairros;
import br.com.amazongas.model.Cidades;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.model.RevendasBairro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BDDataManager extends OpenHelper {

	private SQLiteDatabase db;

	public BDDataManager(Context context) {
		super(context);
	}

	public void inserirBairro(Bairros bairro) {
		ContentValues cv = new ContentValues();
		cv.put("codbairro", bairro.getCodBairro());
		cv.put("nomebairro", bairro.getNomeBairro());
		cv.put("estado", bairro.getEstadoBairro());
		cv.put("codcidade", bairro.getCodcidade());
		db = this.getWritableDatabase();
		db.insertOrThrow("bairro", null, cv);
		db.close();
	}
	
	public void inserirBairroParams(int codbairro, String nomebairro, String estado, String codcidade) {
		ContentValues cv = new ContentValues();
		cv.put("codbairro", codbairro);
		cv.put("nomebairro", nomebairro);
		cv.put("estado", estado);
		cv.put("codcidade", codcidade);
		db = this.getWritableDatabase();
		db.insertOrThrow("bairro", null, cv);
		db.close();
	}

	public void inserirCidade(Cidades cidade) {
		ContentValues cv = new ContentValues();
		cv.put("nomecidade", cidade.getNome());
		cv.put("uf", cidade.getUf());
		cv.put("codcidade", cidade.getCodcidade());
		cv.put("codibge", cidade.getCodcidade());
		db = this.getWritableDatabase();
		db.insertOrThrow("cidade", null, cv);
		db.close();
		Log.d("Consumidor", "Cidade -> " +cidade.getCodcidade());
	}
	
	public void inserirCidadeParams(String nomecidade, String uf, String codcidade, String codibge) {
		ContentValues cv = new ContentValues();
		cv.put("nomecidade", nomecidade);
		cv.put("uf", uf);
		cv.put("codcidade", codcidade);
		cv.put("codibge", codibge);
		db = this.getWritableDatabase();
		db.insertOrThrow("cidade", null, cv);
		db.close();
		Log.d("Consumidor", "Cidade -> " +codcidade);
	}
	
	public void inserirRevenda(Revendas revenda) {
		ContentValues cv = new ContentValues();
		cv.put("nomelocalidade", revenda.getNomelocalidade());
		cv.put("endereco", revenda.getEndereco());
		cv.put("bairro", revenda.getBairro().toString().trim());
		cv.put("telefone1", revenda.getTelefone1());
		cv.put("telefone2", revenda.getTelefone2());
		cv.put("pontoreferencia", revenda.getPontoreferencia());
		cv.put("codcidade", revenda.getCodcidade());
		cv.put("latitude", revenda.getLatitude());
		cv.put("longitude", revenda.getLongitude());
		db = this.getWritableDatabase();
		db.insertOrThrow("revenda", null, cv);
		db.close();
	}
	
	// ---------------------------------------------------------------------------------------------
	public List<Bairros> selectBairros() {

		db = this.getReadableDatabase();
		String sql = "select codbairro, nomebairro, estado from  bairro ";

		Cursor c = db.rawQuery(sql, null);

		List<Bairros> list = new ArrayList<Bairros>();

		while (c.moveToNext()) {
			Bairros b = new Bairros();
			b.setCodBairro(c.getInt(c.getColumnIndex("codbairro")));
			b.setNomeBairro(c.getString(c.getColumnIndex("nomebairro")));
			b.setEstadoBairro(c.getString(c.getColumnIndex("estado")));
			list.add(b);
			
			Log.d("Consumidor", "Bairro -> " + b.getNomeBairro());
		}
		db.close();
		c.close();

		return list;

	}
	
	
	public List<Cidades> selectCidades() {

		db = this.getReadableDatabase();
		String sql = "select nomecidade, uf, codcidade from  cidade ";

		Cursor c = db.rawQuery(sql, null);

		List<Cidades> list = new ArrayList<Cidades>();

		while (c.moveToNext()) {
			Cidades b = new Cidades();
			b.setNome(c.getString(c.getColumnIndex("nomecidade")));
			b.setUf(c.getString(c.getColumnIndex("uf")));
			b.setCodcidade(c.getString(c.getColumnIndex("codcidade")));
			list.add(b);
			
			Log.d("Consumidor", "Cidades -> " + b.getNome());
		}
		db.close();
		c.close();

		return list;

	}
	
	public List<RevendasBairro> selectRevendasCidade(String codcidade) {
		Log.d("Consumidor", "codcidade -> " +codcidade);
		db = this.getReadableDatabase();
		String sql = "select bairro, codcidade, count(bairro) as qtd  from revenda where  rtrim(nomelocalidade) <> 'null' and rtrim(bairro) <> 'null' and trim(codcidade) = '"+codcidade+"' group by bairro, codcidade ";
		Log.i("Consumidor", "sql -> " + sql);
		
		Cursor c = db.rawQuery(sql, null);
		Log.i("Consumidor", "Cursor -> " + c.toString());

		List<RevendasBairro> list = new ArrayList<RevendasBairro>();

		while (c.moveToNext()) {
			RevendasBairro b = new RevendasBairro();
			b.setBairro(c.getString(c.getColumnIndex("bairro")));
			b.setQtd(c.getInt(c.getColumnIndex("qtd")));
			b.setCodcidade(c.getString(c.getColumnIndex("codcidade")));
			//b.setCodcidade("");
			  list.add(b);
			
			Log.d("Consumidor", "Cidades AA -> " + b.getBairro()+ "QTD - "+b.getQtd());
			//Log.d("Consumidor", "codcidade AA-> " +c.getString(c.getColumnIndex("codcidade")));
		}
		db.close();
		c.close();
		Log.i("Consumidor", "List -> " + list);
		return list;
	}
	
	public List<Revendas> selectRevendasBairro(String bairro, String codcidade) {
		db = this.getReadableDatabase();
		Log.i("teste","Detalhes SQLITE >>>>>>>>> b-"+bairro.trim()+" / c-"+codcidade.trim());
		String sBairro = bairro.toString().trim();
		String sCodCidade = "%"+codcidade.toString().trim()+"%";
		
		
		String sql = "select nomelocalidade, endereco, bairro, telefone1, telefone2, pontoreferencia, codcidade, latitude, longitude from revenda "+
                     "where nomelocalidade <> 'null' and rtrim(bairro) = '"+sBairro+"' and codcidade LIKE '"+sCodCidade+"'";
//"where nomelocalidade <> 'null' and bairro LIKE '%BAIRRO DA PAZ%' and codcidade LIKE '%130.260-4%'";

		Cursor c = db.rawQuery(sql, null);

		List<Revendas> list = new ArrayList<Revendas>();

		while (c.moveToNext()) {
			Revendas b = new Revendas();
			b.setNomelocalidade(c.getString(c.getColumnIndex("nomelocalidade")));
			b.setEndereco(c.getString(c.getColumnIndex("endereco")));
			b.setBairro(c.getString(c.getColumnIndex("bairro")));
			b.setTelefone1(c.getString(c.getColumnIndex("telefone1")));
			b.setTelefone2(c.getString(c.getColumnIndex("telefone2")));
			b.setPontoreferencia(c.getString(c.getColumnIndex("pontoreferencia")));
			b.setCodcidade(c.getString(c.getColumnIndex("codcidade")));
			b.setLatitude(c.getString(c.getColumnIndex("latitude")));
			b.setLongitude(c.getString(c.getColumnIndex("longitude")));
			list.add(b);
			
			Log.d("Consumidor", "Cidades -> " + b.getNomelocalidade());
		}
		db.close();
		c.close();
		return list;
	}
	
	
	public void limparBairros(){
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete("bairro",null,null);
	    db.close();
	}
	
	public void limparCidades(){
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete("cidade",null,null);
	    db.close();
	}
	

	// ---------------------------------------------------------------------------------------------
	public boolean insereBairrosManaus() {
		try{
		limparBairros();
		inserirBairroParams(634,"31 DE MARCO","AM","130.260-4");
		inserirBairroParams(1,"ADRIANOPOLIS","AM","130.260-4");
		inserirBairroParams(2,"AEROPORTO","AM","130.260-4");
		inserirBairroParams(4,"AJURICABA","AM","130.260-4");
		inserirBairroParams(6,"ALEIXO","AM","130.260-4");
		inserirBairroParams(8,"ALEIXO OURO VERDE","AM","130.260-4");
		inserirBairroParams(948,"ALFREDO NASCIMENTO","AM","130.260-4");
		inserirBairroParams(572,"ALVORADA","AM","130.260-4");
		inserirBairroParams(18,"ALVORADA I","AM","130.260-4");
		inserirBairroParams(488,"ALVORADA II","AM","130.260-4");
		inserirBairroParams(20,"ALVORADA III","AM","130.260-4");
		inserirBairroParams(24,"AMAZONINO MENDES","AM","130.260-4");
		inserirBairroParams(25,"APARECIDA","AM","130.260-4");
		inserirBairroParams(26,"ARMANDO MENDES","AM","130.260-4");
		inserirBairroParams(27,"ARUANA - COMPENSA","AM","130.260-4");
		inserirBairroParams(31,"BAIRRO DA PAZ","AM","130.260-4");
		inserirBairroParams(29,"BAIRRO UNIAO","AM","130.260-4");
		inserirBairroParams(944,"BASE DA PETROBRAS","AM","130.260-4");
		inserirBairroParams(437,"BEIJA-FLOR II","AM","130.260-4");
		inserirBairroParams(34,"BEIRA MAR","AM","130.260-4");
		inserirBairroParams(36,"BETANIA","AM","130.260-4");
		inserirBairroParams(429,"BLOCO MATISSE","AM","130.260-4");
		inserirBairroParams(39,"BOULEVARD","AM","130.260-4");
		inserirBairroParams(40,"BRAGA MENDES","AM","130.260-4");
		inserirBairroParams(41,"BROOKLIM NOVO","AM","130.260-4");
		inserirBairroParams(51,"CACHOEIRINHA","AM","130.260-4");
		inserirBairroParams(54,"CAMPO DOURADO","AM","130.260-4");
		inserirBairroParams(55,"CAMPOS ELISEOS","AM","130.260-4");
		inserirBairroParams(57,"CAMPUS-ALEIXO","AM","130.260-4");
		inserirBairroParams(61,"CASTELO BRANCO","AM","130.260-4");
		inserirBairroParams(463,"CEASA","AM","130.260-4");
		inserirBairroParams(66,"CENTRO","AM","130.260-4");
		inserirBairroParams(69,"CHACARAS REUNIDAS","AM","130.260-4");
		inserirBairroParams(72,"CHAPADA","AM","130.260-4");
		inserirBairroParams(595,"CIDADE DE DEUS","AM","130.260-4");
		inserirBairroParams(592,"CIDADE DO LESTE","AM","130.260-4");
		inserirBairroParams(569,"CIDADE NOVA","AM","130.260-4");
		inserirBairroParams(80,"CIDADE NOVA I","AM","130.260-4");
		inserirBairroParams(81,"CIDADE NOVA II","AM","130.260-4");
		inserirBairroParams(82,"CIDADE NOVA III","AM","130.260-4");
		inserirBairroParams(83,"CIDADE NOVA IV","AM","130.260-4");
		inserirBairroParams(86,"CIDADE NOVA V","AM","130.260-4");
		inserirBairroParams(87,"CJ AMERICO MEDEIROS","AM","130.260-4");
		inserirBairroParams(88,"CJ BARRA BELA PQ.10","AM","130.260-4");
		inserirBairroParams(91,"CJ JARDIM PAULISTA","AM","130.260-4");
		inserirBairroParams(89,"CJ SUB TENT SARG AMZ","AM","130.260-4");
		inserirBairroParams(90,"CJ TOCANTINS","AM","130.260-4");
		inserirBairroParams(93,"COL ANTONIO ALEIXO","AM","130.260-4");
		inserirBairroParams(96,"COL OLIVEIRA MACHADO","AM","130.260-4");
		inserirBairroParams(97,"COL SANTO ANTONIO","AM","130.260-4");
		inserirBairroParams(431,"COL TERRA NOVA","AM","130.260-4");
		inserirBairroParams(496,"COL TERRA NOVA II","AM","130.260-4");
		inserirBairroParams(915,"COLINA DO ALEIXO","AM","130.260-4");
		inserirBairroParams(906,"COM. AGRIC. SAO JOAO","AM","130.260-4");
		inserirBairroParams(925,"COMPENSA","AM","130.260-4");
		inserirBairroParams(116,"COMPENSA I","AM","130.260-4");
		inserirBairroParams(117,"COMPENSA II","AM","130.260-4");
		inserirBairroParams(118,"COMPENSA III","AM","130.260-4");
		inserirBairroParams(638,"COMUNIDADE SHARP","AM","130.260-4");
		inserirBairroParams(523,"COND. EFIGENIO SALES","AM","130.260-4");
		inserirBairroParams(121,"CONJ.  ARUANA","AM","130.260-4");
		inserirBairroParams(128,"CONJ. BELVEDERE","AM","130.260-4");
		inserirBairroParams(907,"CONJ. BEN HUR","AM","130.260-4");
		inserirBairroParams(129,"CONJ. CAMPOS ELISEOS","AM","130.260-4");
		inserirBairroParams(130,"CONJ. CANARANAS","AM","130.260-4");
		inserirBairroParams(131,"CONJ. JD. ASTECA","AM","130.260-4");
		inserirBairroParams(133,"CONJ. MANOA I","AM","130.260-4");
		inserirBairroParams(124,"CONJ. NOVA REPUBLICA","AM","130.260-4");
		inserirBairroParams(134,"CONJ. NOVO HORIHONTE","AM","130.260-4");
		inserirBairroParams(125,"CONJ. OURO VERDE","AM","130.260-4");
		inserirBairroParams(492,"COROADO","AM","130.260-4");
		inserirBairroParams(140,"COROADO I","AM","130.260-4");
		inserirBairroParams(141,"COROADO II","AM","130.260-4");
		inserirBairroParams(0,"COROADO III","AM","130.260-4");
		inserirBairroParams(148,"CRESPO","AM","130.260-4");
		inserirBairroParams(602,"DA PAZ","AM","130.260-4");
		inserirBairroParams(3,"DISTRITO DO ARAÇÁ","AM","130.260-4");
		inserirBairroParams(165,"DISTRITO INDUSTRIAL","AM","130.260-4");
		inserirBairroParams(603,"DISTRITO INDUSTRIAL","AM","130.260-4");
		inserirBairroParams(627,"DISTRITO INDUSTRIAL II","AM","130.260-4");
		inserirBairroParams(123,"DOM PEDRO","AM","130.260-4");
		inserirBairroParams(433,"DOM PEDRO I","AM","130.260-4");
		inserirBairroParams(154,"DOM PEDRO II","AM","130.260-4");
		inserirBairroParams(171,"EDUCANDOS","AM","130.260-4");
		inserirBairroParams(637,"ENGENHO","AM","130.260-4");
		inserirBairroParams(601,"FAZENDINHA","AM","130.260-4");
		inserirBairroParams(903,"FLAMANAL","AM","130.260-4");
		inserirBairroParams(181,"FLORES","AM","130.260-4");
		inserirBairroParams(184,"FLORESTAL","AM","130.260-4");
		inserirBairroParams(598,"GILBERTO MESTRINHO","AM","130.260-4");
		inserirBairroParams(188,"GLORIA","AM","130.260-4");
		inserirBairroParams(189,"GRANDE CIRCULAR","AM","130.260-4");
		inserirBairroParams(556,"GRANDE VITORIA","AM","130.260-4");
		inserirBairroParams(470,"HILEIA I","AM","130.260-4");
		inserirBairroParams(192,"HILEIA II","AM","130.260-4");
		inserirBairroParams(197,"JAGUARA","AM","130.260-4");
		inserirBairroParams(952,"JAPIIM","AM","130.260-4");
		inserirBairroParams(202,"JAPIIM I","AM","130.260-4");
		inserirBairroParams(203,"JAPIIM II","AM","130.260-4");
		inserirBairroParams(204,"JAPIIMLANDIA","AM","130.260-4");
		inserirBairroParams(207,"JARDIM BRASIL","AM","130.260-4");
		inserirBairroParams(650,"JARDIM FLORESTAL","AM","130.260-4");
		inserirBairroParams(145,"JARDIM MARIA FERNANDA","AM","130.260-4");
		inserirBairroParams(643,"JARDIM MAUA","AM","130.260-4");
		inserirBairroParams(208,"JARDIM PETROPOLIS","AM","130.260-4");
		inserirBairroParams(591,"JESUS ME DEU","AM","130.260-4");
		inserirBairroParams(215,"JOÃO PAULO","AM","130.260-4");
		inserirBairroParams(218,"JOÃO PAULO II","AM","130.260-4");
		inserirBairroParams(217,"JOÃO PAULO III","AM","130.260-4");
		inserirBairroParams(575,"JORGE TEIXEIRA","AM","130.260-4");
		inserirBairroParams(224,"JORGE TEIXEIRA I","AM","130.260-4");
		inserirBairroParams(225,"JORGE TEIXEIRA II","AM","130.260-4");
		inserirBairroParams(227,"JORGE TEXEIRA  III","AM","130.260-4");
		inserirBairroParams(639,"JOSE BONIFACIO","AM","130.260-4");
		inserirBairroParams(229,"KISSIA","AM","130.260-4");
		inserirBairroParams(633,"LAGO AZUL","AM","130.260-4");
		inserirBairroParams(232,"LAGOA VERDE","AM","130.260-4");
		inserirBairroParams(236,"LIRIO DO VALE I","AM","130.260-4");
		inserirBairroParams(237,"LIRIO DO VALE II","AM","130.260-4");
		inserirBairroParams(475,"MANAUS","AM","130.260-4");
		inserirBairroParams(239,"MANOA II","AM","130.260-4");
		inserirBairroParams(245,"MAUAZINHO","AM","130.260-4");
		inserirBairroParams(251,"MONTE DAS OLIVEIRAS","AM","130.260-4");
		inserirBairroParams(252,"MONTE SINAI","AM","130.260-4");
		inserirBairroParams(574,"MORADA DO SOL","AM","130.260-4");
		inserirBairroParams(254,"MORRO DA LIBERDADE","AM","130.260-4");
		inserirBairroParams(493,"MUNDO NOVO","AM","130.260-4");
		inserirBairroParams(255,"MUTIRAO","AM","130.260-4");
		inserirBairroParams(600,"N SR° DE FATIMA","AM","130.260-4");
		inserirBairroParams(271,"NOSSA SEN. DAS GRACAS","AM","130.260-4");
		inserirBairroParams(912,"NOSSA SEN. DO P. SOCORRO","AM","130.260-4");
		inserirBairroParams(564,"NOSSA SENHORA DE FATIMA","AM","130.260-4");
		inserirBairroParams(580,"NOSSA SENHORA DE FATIMA II","AM","130.260-4");
		inserirBairroParams(942,"NOVA CIDADE","AM","130.260-4");
		inserirBairroParams(649,"NOVA CONQUISTA","AM","130.260-4");
		inserirBairroParams(934,"NOVA ESPERANÇA I","AM","130.260-4");
		inserirBairroParams(276,"NOVA ESPERANCA II","AM","130.260-4");
		inserirBairroParams(277,"NOVA FLORESTA","AM","130.260-4");
		inserirBairroParams(279,"NOVA REPUBLICA","AM","130.260-4");
		inserirBairroParams(280,"NOVO ALEIXO","AM","130.260-4");
		inserirBairroParams(571,"NOVO ISRAEL","AM","130.260-4");
		inserirBairroParams(283,"NOVO ISRAEL I","AM","130.260-4");
		inserirBairroParams(641,"NOVO REINO","AM","130.260-4");
		inserirBairroParams(919,"OSVALDO CRUZ","AM","130.260-4");
		inserirBairroParams(289,"OSVALDO FROTA 1","AM","130.260-4");
		inserirBairroParams(290,"OURO VERDE","AM","130.260-4");
		inserirBairroParams(941,"PANTANAL","AM","130.260-4");
		inserirBairroParams(589,"PARQ RIACHUELO","AM","130.260-4");
		inserirBairroParams(295,"PARQ.DAS LARANJEIRAS","AM","130.260-4");
		inserirBairroParams(298,"PARQUE 10 DE NOVEMBRO","AM","130.260-4");
		inserirBairroParams(299,"PARQUE DAS NACOES","AM","130.260-4");
		inserirBairroParams(590,"PARQUE RIACHUELO","AM","130.260-4");
		inserirBairroParams(304,"PCA 14 DE JANEIRO","AM","130.260-4");
		inserirBairroParams(306,"PETROPOLIS","AM","130.260-4");
		inserirBairroParams(308,"PLANALTO","AM","130.260-4");
		inserirBairroParams(311,"PONTA NEGRA","AM","130.260-4");
		inserirBairroParams(321,"PQ. DOS ESTADOS","AM","130.260-4");
		inserirBairroParams(322,"PQ. RES. PONTA NEGRA","AM","130.260-4");
		inserirBairroParams(562,"PRAÇA 14 DE JANEIRO","AM","130.260-4");
		inserirBairroParams(328,"PRESIDENTE VARGAS","AM","130.260-4");
		inserirBairroParams(330,"PURAQUEQUARA","AM","130.260-4");
		inserirBairroParams(331,"RAIZ","AM","130.260-4");
		inserirBairroParams(623,"RECIFE","AM","130.260-4");
		inserirBairroParams(333,"REDENCAO","AM","130.260-4");
		inserirBairroParams(334,"RIACHO DOCE","AM","130.260-4");
		inserirBairroParams(617,"RIO PIORINI","AM","130.260-4");
		inserirBairroParams(340,"SANTA ETELVINA","AM","130.260-4");
		inserirBairroParams(341,"SANTA INES","AM","130.260-4");
		inserirBairroParams(342,"SANTA LUZIA","AM","130.260-4");
		inserirBairroParams(346,"SANTO AGOSTINHO","AM","130.260-4");
		inserirBairroParams(345,"SANTO ANTONIO","AM","130.260-4");
		inserirBairroParams(348,"SAO FRANCISCO","AM","130.260-4");
		inserirBairroParams(349,"SAO GERALDO","AM","130.260-4");
		inserirBairroParams(350,"SAO JOAO","AM","130.260-4");
		inserirBairroParams(351,"SAO JORGE","AM","130.260-4");
		inserirBairroParams(921,"SAO JOSE","AM","130.260-4");
		inserirBairroParams(357,"SAO JOSE DOS CAMPOS","AM","130.260-4");
		inserirBairroParams(358,"SAO JOSE I","AM","130.260-4");
		inserirBairroParams(359,"SAO JOSE II","AM","130.260-4");
		inserirBairroParams(360,"SAO JOSE III","AM","130.260-4");
		inserirBairroParams(422,"SAO JOSE IV","AM","130.260-4");
		inserirBairroParams(541,"SAO JOSE OPERARIO","AM","130.260-4");
		inserirBairroParams(139,"SAO JUDAS TADEU","AM","130.260-4");
		inserirBairroParams(363,"SAO LAZARO","AM","130.260-4");
		inserirBairroParams(364,"SAO LUCAS","AM","130.260-4");
		inserirBairroParams(365,"SAO PAULO","AM","130.260-4");
		inserirBairroParams(366,"SAO RAIMUNDO","AM","130.260-4");
		inserirBairroParams(368,"SAO SEBASTIAO","AM","130.260-4");
		inserirBairroParams(511,"SENADOR HÉLIO CAMPOS","AM","130.260-4");
		inserirBairroParams(484,"SHANGRILA","AM","130.260-4");
		inserirBairroParams(372,"STO AGOSTINHO","AM","130.260-4");
		inserirBairroParams(461,"STO AGOSTINHO II","AM","130.260-4");
		inserirBairroParams(376,"STOS DUMONT","AM","130.260-4");
		inserirBairroParams(379,"TANCREDO NEVES","AM","130.260-4");
		inserirBairroParams(384,"TARUMA","AM","130.260-4");
		inserirBairroParams(620,"TARUMÃ-AÇU","AM","130.260-4");
		inserirBairroParams(387,"TERRA NOVA","AM","130.260-4");
		inserirBairroParams(389,"TERRA NOVA II","AM","130.260-4");
		inserirBairroParams(916,"TRIANGULO","AM","130.260-4");
		inserirBairroParams(392,"UNIAO DA VITORIA","AM","130.260-4");
		inserirBairroParams(395,"V8 - ALEIXO","AM","130.260-4");
		inserirBairroParams(930,"VALE DO AMANHECER","AM","130.260-4");
		inserirBairroParams(559,"VALE DO SINAI","AM","130.260-4");
		inserirBairroParams(143,"VARZEA","AM","130.260-4");
		inserirBairroParams(629,"VERE ALVES","AM","130.260-4");
		inserirBairroParams(398,"VIERALVES","AM","130.260-4");
		inserirBairroParams(918,"VILA BURITI","AM","130.260-4");
		inserirBairroParams(403,"VILA DA FELICIDADE","AM","130.260-4");
		inserirBairroParams(404,"VILA DA PRATA","AM","130.260-4");
		inserirBairroParams(410,"VILHA GUILHERME","AM","130.260-4");
		inserirBairroParams(653,"VILLA RIO NEGRO","AM","130.260-4");
		inserirBairroParams(963,"ZONA DE EXPANSAO URBANA","AM","130.260-4");
		inserirBairroParams(411,"ZONA RURAL","AM","130.260-4");
		inserirBairroParams(545,"ZUMBI DOS PALMARES","AM","130.260-4");
		inserirBairroParams(417,"ZUMBI I","AM","130.260-4");
		inserirBairroParams(418,"ZUMBI II","AM","130.260-4");
		inserirBairroParams(419,"ZUMBI III","AM","130.260-4");	
		} catch (Exception e) {
			return false;
		}
		return true;
	}	
	
	// ---------------------------------------------------------------------------------------------
	public boolean insereCidadeManaus() {
		try{
			limparCidades();
			inserirCidadeParams("MANAUS", "AM", "130.260-4","130.260-3");
		} catch (Exception e) {
			return false;
		}
		return true;
	}	
	
	
	

}
