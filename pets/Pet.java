package pets;

public class Pet {
    private long id = 0;
    private String nome = "";
    private String raca = "";
    private int idade = 0;

    public Pet(){

    }

    public Pet(String nome, String raca, int idade){
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    public long getId()    {
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNome()    {
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getRaca()    {
        return this.raca;
    }

    public void setRaca(String raca){
        this.raca = raca;
    }

    public int getIdade()    {
        return this.idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }
}
