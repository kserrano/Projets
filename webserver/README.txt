Stage 2
-------------------------------------

On lance notre Webserveur qui va créer le socket, le bufferOftask (on reviendra sur cela plus tard), les workers, et le TCPAcceptor. Pour finir, la classe lance en parallèle les workers et le TCPAcceptor.

Le bufferOftask est la classe importante du Stage 2. C'est ce buffer qui va gérer les différentes taches en utilisant 3 sémaphores: un pour les espaces libres, un pour les items libres, et un utile pour le mutex. On retiendra ici les deux méthodes importantes putIntoBuffer et readTask.

La classe Worker a été modifiée par rapport au Stage 1 car elle redistribue son travail aux Task à l'aide du bufferOfTasks

La classe Task génère les réponses aux requêtes dans la méthode run et ferme les connexions une fois que la réponse a été envoyée.

Enfin TCPAcceptor est lancé une fois au démarage et continue de tourner en permanence afin de recevoir les requêtes et les faire traiter par les Worker et Tasks. Il utilise pour cela le bufferOfTask.

Stage 3
-------------------------------------

On lance notre Webserveur qui va instancier deux bufferOfTask: un de Task 1 et un de Task 2 (on reviendra sur la fonction de ces deux différentes classes par la suite). Ensuite, la classe crée un nombre choisi de Worker. Un "worker" se détache en deux Workers concrets: un workerRead et un WorkerProcess. On expliquera l'utilité de ces deux classes par la suite aussi. Il faut noter que ce sont des threads. Enfin, après la création des Workers, la classe crée le TCPAcceptor.

Le WorkerRead sert à lire les Tasks1 arrivant dans le BufferofTask1. Les Task1 servent à lire les requêtes, faire le pipelining et mettre une nouvelle Task2 correspondante à la requête dans le bufferOfTask2 (dans la méthode readRequest. Le Worker distribue ainsi le travail aux différentes Task1.

Le WorkerProcess sert à lire les Task2 dans le BufferOfTask des Task2 avant de les lancer. Le lancement de la Task2 entraine la génération de la réponse à la requête avant de l'envoyer sur le OutputStream (dans la méthode run).

Le counter est une classe héritant de la classe abstraite BlockingCounter. Elle ne comprend que deux méthodes utiles pour le pipelining: increment (qui sert à incrémenter c) et await, qui fait attendre la Task1 quand elle a lu une requête.

BufferOfTask est le même que dans le stage2. Sa généricité nous permet de créer des buffer de Task1 aussi bien que de Task2. On utilise ici 3 sémaphores (voir stage2)

Finalement, le TCPAcceptor la classe qui attend une connexion et qui crée la Task1 et la met dans le buffer de task1, qui servira, rappelons le, pour démarrer tout l'acheminement jusqu'à l'envoi des réponses pour pouvoir naviguer en toute tranquilité sur le serveur.
-------------------------------------


Il serait possible de n'utiliser qu'un seul buffer mais cela entrainerai de la complexité supérieure. En effet, il serait possible de stocker des Task dans un meme buffer en indexant de façon séparée lesquelles seraient utilisées pour les taches de lecture ou de process. Il est nettement préférable d'utiliser deux buffers séparés pour des questions pratiques.