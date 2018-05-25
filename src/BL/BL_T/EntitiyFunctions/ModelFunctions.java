package BL.BL_T.EntitiyFunctions;

import BL.BL_T.Entities.TruckModel;
import DAL.DAL_T.ErrorsHandler;
import DAL.DAL_T.Models;

import java.sql.SQLException;

/**
 * Created by Naama on 21/04/2018.
 */
public class ModelFunctions {

    public static void insertModel(TruckModel model) throws SQLException {
        Models.insertModel(model);
    }

    public static TruckModel retrieveModel(String id) throws SQLException {
        return Models.retrieveModel(id);
    }

    public static void removeModel(String id) throws SQLException {
        Models.removeModel(id);
    }

    public static void updateModel(TruckModel m) throws SQLException, ClassNotFoundException {
        Models.updateModel(m);
    }

    public static boolean isExist(String id) throws Exception {
        return ErrorsHandler.isModelExist(id);
    }

}
