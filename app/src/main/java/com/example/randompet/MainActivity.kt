package com.example.randompet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var pokemonImageURL = ""
    var pokemonWorkArtURL = ""
    var pokemonName = ""
    var pokemonSpecies = ""
    var userInput = ""
    var abilitiesArrayNames = mutableListOf<String>()
    var pokemonType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Randombutton = findViewById<Button>(R.id.buttonRandom)
        var Selectionbutton = findViewById<Button>(R.id.buttonSelection)
        var scrollView: ScrollView = findViewById(R.id.scrollAttribute)
        var abilitiesLayout:LinearLayout = findViewById(R.id.attributeLayout)
        getNextImage(Randombutton,scrollView, abilitiesLayout)
        Selectionbutton.setOnClickListener {
            val input = findViewById<EditText>(R.id.userInputName).text.toString()
            if (input.isNotEmpty()) {
                getPokemonImageURL(input, abilitiesLayout)
            }
        }
    }

    private fun getPokemonImageURL(name:String, attLayout: LinearLayout){
        var apiLink:String
        val client = AsyncHttpClient()
        if (name.isEmpty()){
            val id = Random.nextInt(0,1001)
            apiLink =  "https://pokeapi.co/api/v2/pokemon/$id/"
        }
        else{
            var lowercase_name= name.lowercase()
            apiLink = "https://pokeapi.co/api/v2/pokemon/$lowercase_name/"
        }
        client[apiLink, object : JsonHttpResponseHandler(){

            override fun onSuccess(statusCode:Int, headers: Headers, json: JsonHttpResponseHandler.JSON){
                Log.d("Pokemon", "response successful$json")
                pokemonName = json.jsonObject.getString("name")
                findViewById<TextView>(R.id.namePoke).text=pokemonName.capitalize()
                pokemonSpecies = json.jsonObject.getJSONObject("species").getString("name") //get the species name

                pokemonImageURL = json.jsonObject.getJSONObject("sprites").getString("front_default")

                pokemonWorkArtURL = json.jsonObject.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default")

                val typesArray = json.jsonObject.getJSONArray("types")
                pokemonType = typesArray.getJSONObject(0).getJSONObject("type").getString("name")
                findViewById<TextView>(R.id.typeName).text = pokemonType

                var abilitiesArray = json.jsonObject.getJSONArray("abilities")

                attLayout.removeAllViews()
                abilitiesArrayNames.clear()

                for (i in 0 until abilitiesArray.length()){
                    abilitiesArrayNames.add(abilitiesArray.getJSONObject(i).getJSONObject("ability").getString("name"))
                }
                for (ability in abilitiesArrayNames){
                    val abilityTextView = TextView(this@MainActivity)
                    abilityTextView.text = ability
                    attLayout.addView(abilityTextView)
                }
                loadImageWithGlide()
                Log.d("pokemonImageURL", "Pokemon image URL set")
            }
            override fun onFailure(
                statusCode: Int,
                header: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ){
                Log.d("Pokemon", errorResponse)
            }
        }]
    }
    private fun loadImageWithGlide() {
        if (!pokemonImageURL.isEmpty()) {
            val imageView: ImageView = findViewById(R.id.pokemonImage)
            Glide.with(this)
                .load(pokemonImageURL)
                .override(299, 200)
                .into(imageView)
        } else {
            Log.d("Pokemon Error", "Image URL is empty or null")
        }
        if (!pokemonWorkArtURL.isEmpty()){
            val workartView: ImageView = findViewById(R.id.artwork)
            Glide.with(this)
                .load(pokemonWorkArtURL)
                .override(103,175)
                .into(workartView)
        }
    }

    private fun getNextImage(button: Button,  scrollAtt: ScrollView, attLayout: LinearLayout) {

            button.setOnClickListener{

                if (button == findViewById(R.id.buttonSelection)) {
                    getPokemonImageURL(findViewById<EditText>(R.id.userInputName).text.toString(), attLayout)
                }
                else{
                    getPokemonImageURL("", attLayout)
            }
            scrollAtt.post {
                scrollAtt.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }

    }
}