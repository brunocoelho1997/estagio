package hello.Transaction;

import hello.Enums.Frequency;
import hello.Enums.Genre;
import hello.SubType.SubType;
import hello.Type.Type;

import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDate;

public class Transaction_ {

    public static volatile SingularAttribute<Transaction, String> name;
    public static volatile SingularAttribute<Transaction, String> description;
    public static volatile SingularAttribute<Transaction, Float> value;
    public static volatile SingularAttribute<Transaction, Frequency> frequency;
    public static volatile SingularAttribute<Transaction, Genre> genre;
    public static volatile SingularAttribute<Transaction, Type> type;
    public static volatile SingularAttribute<Transaction, SubType> subType;
    public static volatile SingularAttribute<Transaction, LocalDate> date;

}
