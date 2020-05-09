package DataBase;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAOTEMP<T> {
        /**
         * this interface only for 1 table
         * @return
         */

        //static DAOTEMP<T> getInstance();

        String getTableName();

        T get(String id);

        List<T> getAll();

        void save(T t) throws SQLException;

        void update(T t, String[] params);

        void delete(T t);

        boolean exist(T t);



}
