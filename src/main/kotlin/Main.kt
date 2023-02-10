fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class User(val postings:MutableList<String>){
    fun post(message: String){
        postings.add(message)
    }

    fun view():List<String>{
        return this.postings
    }
}