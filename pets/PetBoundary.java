package pets;

import java.time.LocalDate;

import javax.management.modelmbean.InvalidTargetObjectTypeException;

import edu.curso.Contato;
import edu.curso.ContatoBoundary;
import edu.curso.ContatoControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class PetBoundary extends Application{
    private Label lblId = new Label("");
    private TextField txtNome = new TextField();
    private TextField txtRaca = new TextField();
    private TextField txtIdade = new TextField();

    private PetControl control = new PetControl();

    private TableView<Pet> tableView = new TableView<>();

    @Override
    public void start(Stage stage) {
        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction( e -> control.gravar() );
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( e -> control.pesquisar() );

        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lblId, 1, 0);
        paneForm.add(new Label("Nome: "), 0, 1);
        paneForm.add(txtNome, 1, 1);
        paneForm.add(new Label("Raça: "), 0, 2);
        paneForm.add(txtRaca, 1, 2);
        paneForm.add(new Label("Idade: "), 0, 3);
        paneForm.add(txtIdade, 1, 3);

        paneForm.add(btnGravar, 0, 5);
        paneForm.add(btnPesquisar, 1, 5);

        ligacoes();
        gerarColunas();

        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter(tableView);

        Scene scn = new Scene(panePrincipal, 600, 400);
        stage.setScene(scn);
        stage.setTitle("Agenda de Contatos");
        stage.show();
    }

    public void gerarColunas() { 
        TableColumn<Pet, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory( new PropertyValueFactory<Pet, Long>("id") );

        TableColumn<Pet, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory( new PropertyValueFactory<Pet, String>("nome"));

        TableColumn<Pet, String> col3 = new TableColumn<>("Raca");
        col3.setCellValueFactory( new PropertyValueFactory<Pet, String>("raca"));

        TableColumn<Pet, Integer> col4 = new TableColumn<>("Idade");
        col4.setCellValueFactory( new PropertyValueFactory<Pet, Integer>("idade"));

        tableView.getSelectionModel().selectedItemProperty()
            .addListener( (obs, antigo, novo) -> {
                if (novo != null) { 
                    System.out.println("Nome: " + novo.getNome());
                    control.paraTela(novo);
                }
            });
        Callback<TableColumn<Pet, Void>, TableCell<Pet, Void>> cb = 
            new Callback<>() {
                @Override
                public TableCell<Pet, Void> call(
                    TableColumn<Pet, Void> param) {
                    TableCell<Pet, Void> celula = new TableCell<>() { 
                        final Button btnApagar = new Button("Apagar");

                        {
                            btnApagar.setOnAction( e -> {
                                Pet p = tableView.getItems().get( getIndex() );
                                control.excluir( p ); 
                            });
                        }

                        @Override
                        public void updateItem( Void item, boolean empty) {                             
                            if (!empty) { 
                                setGraphic(btnApagar);
                            } else { 
                                setGraphic(null);
                            }
                        }
                        
                    };
                    return celula;            
                } 
            };

        TableColumn<Pet, Void> col5 = new TableColumn<>("Ação");
        col6.setCellFactory(cb);

        tableView.getColumns().addAll(col1, col2, col3, col4, col5);
        tableView.setItems( control.getLista() );
    }

    public void ligacoes() { 
        control.idProperty().addListener( (obs, antigo, novo) -> {
            lblId.setText( String.valueOf(novo) );
        });
        Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(control.racaProperty(), txtRaca.textProperty());
        IntegerStringConverter converter = new IntegerStringConverter();
        Bindings.bindBidirectional(txtIdade.textProperty(), control.idadeProperty(), (StringConverter) converter);
    }

    public static void main(String[] args) {
        Application.launch(PetBoundary.class, args);
    }
}
