package com.pomajulca.paginasamarrillas.repositories;

import com.pomajulca.paginasamarrillas.models.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
   private static List<Company> companies=new ArrayList<>();

    private static List<Company> companys_category;

   static {
       companies.add(new Company(1,"Restaurantes","Cevichería Arco Iris del Norte",
               "Comas - Lima","988 890 900","cevicheria@gmail.com",
               "http://www.restaurantarcoirisdelnorte.com","logo1",
               "Restaurant Arco Iris del Norte, brinda a toda su clientela los más" +
                       " exquisitos platos regionales, nacionales e internaciones." +
                       " Especializado en pescados, mariscos y comida criolla."));
       companies.add(new Company(2,"Restaurantes","Restaurant Pollería el Pollo Real",
                   "Calle Piérola Cercado Arequipa - Arequipa","(054) 235747"
               ,"PolleriaPollo@gmail.com","http://www.elpolloreal.com","logo2",
               "Iniciamos este negocio, con el fin de brindar al público un servicio " +
                       "de calidad no solo presentando un buen producto sino también ofreciendo una atención excelente."));
        companies.add(new Company(3,"Escuelas","Colegio Claretiano","Avenida Parque De Las Leyendas" +
                " 555 San Miguel - Lima","(01) 4515662","colegioClaretiano@gmail.com",
                "http://www.claretiano.edu.pe","logo3",
                "Somos una institución educativa que evangeliza educando, según el carisma de nuestro fundador San" +
                        " Antonio María Claret."));
   }
  public  static List<Company>getCompanies(){return companies;}

  public static Company getCompanyById(Integer id){
       for(Company company: companies){
           if (company.getId().equals(id)){
               return company;
           }
       }
       return null;
  }

  public static List<Company> filterCompaniesByCategory(String category){
       companys_category=new ArrayList<>();
       for (Company company: companies){
           if (company.getCategory().equalsIgnoreCase(category)){
               companys_category.add(company);
           }
       }
       return companys_category;
  }
}
