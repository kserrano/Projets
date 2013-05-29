Stage 2
-------------------------------------

On lance notre Webserveur qui va cr�er le socket, le bufferOftask (on reviendra sur cela plus tard), les workers, et le TCPAcceptor. Pour finir, la classe lance en parall�le les workers et le TCPAcceptor.

Le bufferOftask est la classe importante du Stage 2. C'est ce buffer qui va g�rer les diff�rentes taches en utilisant 3 s�maphores: un pour les espaces libres, un pour les items libres, et un utile pour le mutex. On retiendra ici les deux m�thodes importantes putIntoBuffer et readTask.

La classe Worker a �t� modifi�e par rapport au Stage 1 car elle redistribue son travail aux Task � l'aide du bufferOfTasks

La classe Task g�n�re les r�ponses aux requ�tes dans la m�thode run et ferme les connexions une fois que la r�ponse a �t� envoy�e.

Enfin TCPAcceptor est lanc� une fois au d�marage et continue de tourner en permanence afin de recevoir les requ�tes et les faire traiter par les Worker et Tasks. Il utilise pour cela le bufferOfTask.

Stage 3
-------------------------------------

On lance notre Webserveur qui va instancier deux bufferOfTask: un de Task 1 et un de Task 2 (on reviendra sur la fonction de ces deux diff�rentes classes par la suite). Ensuite, la classe cr�e un nombre choisi de Worker. Un "worker" se d�tache en deux Workers concrets: un workerRead et un WorkerProcess. On expliquera l'utilit� de ces deux classes par la suite aussi. Il faut noter que ce sont des threads. Enfin, apr�s la cr�ation des Workers, la classe cr�e le TCPAcceptor.

Le WorkerRead sert � lire les Tasks1 arrivant dans le BufferofTask1. Les Task1 servent � lire les requ�tes, faire le pipelining et mettre une nouvelle Task2 correspondante � la requ�te dans le bufferOfTask2 (dans la m�thode readRequest. Le Worker distribue ainsi le travail aux diff�rentes Task1.

Le WorkerProcess sert � lire les Task2 dans le BufferOfTask des Task2 avant de les lancer. Le lancement de la Task2 entraine la g�n�ration de la r�ponse � la requ�te avant de l'envoyer sur le OutputStream (dans la m�thode run).

Le counter est une classe h�ritant de la classe abstraite BlockingCounter. Elle ne comprend que deux m�thodes utiles pour le pipelining: increment (qui sert � incr�menter c) et await, qui fait attendre la Task1 quand elle a lu une requ�te.

BufferOfTask est le m�me que dans le stage2. Sa g�n�ricit� nous permet de cr�er des buffer de Task1 aussi bien que de Task2. On utilise ici 3 s�maphores (voir stage2)

Finalement, le TCPAcceptor la classe qui attend une connexion et qui cr�e la Task1 et la met dans le buffer de task1, qui servira, rappelons le, pour d�marrer tout l'acheminement jusqu'� l'envoi des r�ponses pour pouvoir naviguer en toute tranquilit� sur le serveur.
-------------------------------------


Il serait possible de n'utiliser qu'un seul buffer mais cela entrainerai de la complexit� sup�rieure. En effet, il serait possible de stocker des Task dans un meme buffer en indexant de fa�on s�par�e lesquelles seraient utilis�es pour les taches de lecture ou de process. Il est nettement pr�f�rable d'utiliser deux buffers s�par�s pour des questions pratiques.