package com.example.listadepersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.listadepersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    // Variaveis dos nomes criados para as paginas

    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Contato";
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar o Contato" ;

    // Variaveis dos campos que podemos preencher em nosso aplicativo

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private EditText campoCEP;
    private EditText campoGenero;
    private EditText campoRG;


    // Variavel que esta criando a referencia para o personagem

    private final PersonagemDAO dao = new PersonagemDAO();

    private Personagem personagem;

    // Criando o menu do formulario
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate( R.menu.activity_formulario_personagem_menu_salvar,menu);
        return super.onCreateOptionsMenu( menu );
    }

    // Metodo que esta permitindo o clique

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_personagem_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected( item );
    }

    // Inicializando o app ele faz os ajustes do layout e inicializa assim os metodos

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario_personagem );
        inicializaCampos();
        configuraBotaoSalvar();
        carregaPersonagem();
    }

    // Metodo carregando os personagens criados

    private void carregaPersonagem() {

        Intent dados = getIntent();
        if (dados.hasExtra( CHAVE_PERSONAGEM )) {

            setTitle( TITULO_APPBAR_EDITA_PERSONAGEM );
            personagem = (Personagem) dados.getSerializableExtra( CHAVE_PERSONAGEM );
            preencheCampos();
        } else {

            setTitle( TITULO_APPBAR_NOVO_PERSONAGEM );
            personagem = new Personagem();
        }
    }

    // Metodo permitindo os campos serem preenchidos com sucesso

    private void preencheCampos() {

        campoNome.setText( personagem.getNome() );
        campoAltura.setText( personagem.getAltura() );
        campoNascimento.setText( personagem.getNascimento() );
        campoTelefone.setText(personagem.getTelefone());
        campoEndereco.setText(personagem.getEndereco());
        campoCEP.setText(personagem.getCEP());
        campoGenero.setText(personagem.getGenero());
        campoRG.setText(personagem.getRG());

    }

    // Metodo realizando os saves das informações

    private void configuraBotaoSalvar() {

        Button botaoSalvar = findViewById( R.id.button_salvar );
        botaoSalvar.setOnClickListener( new View.OnClickListener() {

            @Override

            // Ao apertar o botão esta chamando o metodo de finalização

            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    // Metodo em que finalizara o preenchimento do formulario

    private void finalizaFormulario() {

        preenchePersonagem();

        // Utilizando o metodo de edição criado no DAO quando o personagem ja existe

        if (personagem.IdValido()) {

            dao.edita( personagem );

            // Utilizando o metodo de salvar criado no DAO para fazer um novo personagem, se nao existir o mesmo

        } else {

            dao.salva( personagem );
        }
        finish();
    }

    // Metodo referenciando as variveis dos campos, aos campos que criamos no layout

    private void inicializaCampos() {

        campoNome = findViewById( R.id.editText_nome );
        campoAltura = findViewById( R.id.editText_altura );
        campoNascimento = findViewById( R.id.editText_nascimento );
        campoTelefone = findViewById(R.id.editText_telefone);
        campoEndereco = findViewById(R.id.editText_endereco);
        campoCEP = findViewById(R.id.editText_cep);
        campoGenero = findViewById(R.id.editText_genero);
        campoRG = findViewById(R.id.editText_rg);



        // Criando a mascara no campo Altura, assim fazendo o digito ser separado por virgula apos o primeiro digito

        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher( campoAltura, smfAltura );
        campoAltura.addTextChangedListener( mtwAltura );

        // Criando a mascara no campo Nascimento , que faz o digito ser separado por dia/mes/ano

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher( campoNascimento, smfNascimento );
        campoNascimento.addTextChangedListener( mtwNascimento );

        // Criando a mascara no campo de Telefone , assim fazendo o digito ser separado por (DDD)99999-9999

        SimpleMaskFormatter smfTelefone = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtwTelefone = new MaskTextWatcher( campoTelefone, smfTelefone );
        campoTelefone.addTextChangedListener( mtwTelefone);

        // Criando a mascara no campo CEP , assim fazendo o digito ser separado por XXXXX-XXX

        SimpleMaskFormatter smfCEP = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwCEP = new MaskTextWatcher( campoCEP, smfCEP);
        campoCEP.addTextChangedListener( mtwCEP );

        // Criando a mascara no campo RG , assim fazendo o digito ser separado por XX.XXX.XXX-X

        SimpleMaskFormatter smfRG = new SimpleMaskFormatter("NN.NNN.NNN-N");
        MaskTextWatcher mtwRG = new MaskTextWatcher( campoRG, smfRG);
        campoRG.addTextChangedListener( mtwRG );



    }
    // Metodo que tem como trabalho preencher todas as variaveis com as informações escritas em nosso campos

    private void preenchePersonagem() {

        // Convertendo os textos escritos no campo para string

        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String CEP = campoCEP.getText().toString();
        String genero = campoGenero.getText().toString();
        String RG = campoRG.getText().toString();


        // Setando as informações

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        personagem.setTelefone(telefone);
        personagem.setEndereco(endereco);
        personagem.setCEP(CEP);
        personagem.setGenero(genero);
        personagem.setRG(RG);

    }
}