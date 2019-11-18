package serpis.ad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class CategoriaDao {
	protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
    public static List getAll() {
        List<Categoria> categorias =  entityManager.createQuery("from Categoria order by Id", 
        		Categoria.class).getResultList();
        return categorias;
    }
    public static void show() {
    	List<Categoria> categorias=getAll();
    	for (Categoria categoria : categorias)
			System.out.printf("%3d %s %n", categoria.getId(), categoria.getNombre());
    }
}

//    private static string selectSql= "select * from categoria where id = @id";
//    public static Categoria Load(object id) {
//        Categoria categoria = new Categoria();
//        IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
//        dbCommand.CommandText = selectSql;
//        DbCommandHelper.AddParameter(dbCommand, "id", id);
//        IDataReader dataReader = dbCommand.ExecuteReader();
//        dataReader.Read();
//        categoria.Id = (ulong)id;
//        categoria.Nombre = (string)dataReader["nombre"];
//        dataReader.Close();
//        return categoria;
//    }
//
//    public static void Save(Categoria categoria) {
//        if (categoria.Id == 0)
//            insert(categoria);
//        else
//            update(categoria);
//    }
//
//    private static string insertSql = "insert into categoria (nombre) values (@nombre)";
//    private static void insert(Categoria categoria) {
//
//    }
//
//    private static string updateSql = "update categoria set nombre=@nombre where id=@id";
//    private static void update(Categoria categoria) {
//        IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
//        dbCommand.CommandText = updateSql;
//        DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
//        DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
//        dbCommand.ExecuteNonQuery();
//    }
//
//    private static string deleteSql = "delete from categoria where id=@id";
//    public static void Delete(object id) {
//
//    }
//}
