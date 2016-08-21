### Leveraging Index Area

* ***Index*** refers to the objects (blobs and trees(?)) that have been newly created by running `git add` command.

* The *index* area provides us the ability to build up next commit in stages. Developers using `git commit -am <message>` really don't use this ability provided by Git.

#### Hands-on

* Let's create a new git repo.
```
mkdir repo && cd repo
git init
```
* At this moment there are no objects in the repo. Executing `find .git/objects -type f | sort` will return nothing.
* Run `ls -al` and notice that the *index* file does not exist.
* Now let's run following commands:
```
echo "Line 1" >> file.txt
git add file.txt
```
* Running `ls -al` will tell us that a new file named *index* is created. Take a note of file size.
* Also, running `find .git/objects -type f | sort` will tell us that a new blob object has been created reprented by the hash-id

#### Take away from Hands-on

* The above activity tells us that `git add` results into *blob* creation.
* WIP


#### git add --patch

* WIP
