package pets;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetControl {
    private ObservableList<Pet> lista = FXCollections.observableArrayList();
    private long contador = 0;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty raca = new SimpleStringProperty("");
    private IntegerProperty idade = new SimpleIntegerProperty(0);


    public PetControl() { 
        lista.add( new Pet("millou","snowshoe", 15));

        lista.add( new Pet("enzo","siames", 12));
        lista.add( new Pet("plinio","ragdoll", 7));
    }

    public Pet paraEntidade() { 
        Pet c = new Pet();
        c.setNome( nome.get() );
        c.setRaca( raca.get() );
        c.setIdade( idade.get() );
        return c;
    }

    public void excluir( Pet c ) { 
        lista.remove(c);
    }

    public void paraTela(Pet c) { 
        if (c != null) {
            nome.set( c.getNome() );
            raca.set( c.getRaca() );
            idade.set( c.getIdade() );
        }
    }


    public void gravar() { 
        this.contador += 1;
        Pet c = paraEntidade();
        c.setId( this.contador );
        lista.add( c );
    }

    public void pesquisar() { 

        for (Pet c : lista) { 
            if (c.getNome().contains( nome.get() )) { 
                paraTela( c );
            }
        }
    }

    public LongProperty idProperty() { 
        return this.id;
    }
    public StringProperty nomeProperty() { 
        return this.nome;
    }
    public StringProperty racaProperty() { 
        return this.raca;
    }
    public IntegerProperty idadeProperty() { 
        return this.idade;
    }

    public ObservableList<Pet> getLista() { 
        return this.lista;
    }
}
