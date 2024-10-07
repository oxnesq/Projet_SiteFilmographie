1.
class Ville (val nom: String, val rating : Int){

}

2.
val castres = Ville("Castres", 2.5)
val perpignan = Ville("Perpignan", 0.1)
val toulouse = Ville("Toulouse", 5)
val villesExo = listOf(castres,perpignan,toulouse)

3.
villesExo.forEach { ville -> if (ville.rating>2){println(ville)}}


6.
class Ville(val nom : String, val : rating: Int){
    fun affStar()="*".repeat(rating)
}

7.
villes.filtrer {it.rating > 2 }.map { it.nom() + " " + it.affStar() }.forEach{ println(it) }