### Understanding `commit` step-by-step

* Every `commit` holds a single `tree`. `Trees` owns `blob`.
* Every `tree` will have same hash-id across different computers, as far as they hold same `blobs` (and sub-trees).

* Following are the steps that Git performs when you fire a `git commit` command for the first time in a new repo:
  1. `git write-tree` will record the contents of the index on a `tree`; and will response back with the hash-id of the tree object just created.
  We don't have a `commit` object yet, but a `tree` object containing the `blob`/s.
  2. `git commit-tree <tree-hash-id>` makes a commit object which holds the said tree. The response is the hash-id of commit object.
  3. Next, our branch `master` should now point to the new commit object. In order to do that,
  `git update-ref refs/heads/master <commit-hash-id>`.
  **Note**: If this is not done, the commit object would have been marked *unreachable*, and in some time (whenever next gc cycle runs) the commit object (along with the trees and blobs) would have been removed.
  `git gc` is the command to run gc, which is normally not required to be run manually.
  Multiple commits are reachable by the `parent` relationship.
  One can open the file *refs/heads/master* in an editor and can see the hash-id associated with it.
  4. Next, point the HEAD to the latest commit in master. `git symbolic-ref HEAD refs/heads/master`


* A single `commit` can have one or more parents. While being at one commit, one can traverse the entire history using the parent link. Eg. You have made 2 commits so far on your repo. The "second commit"'s parent will be the "first commit".

* Because of the above property of the `commit` object, a `git branch` is nothing but a reference to a commit object. `refs/heads/<branch-name>` file carries the hash-id of the commit object. *master* branch is no different.

* `git branch -v` will give you the top-level referenced commit object's hash-id and summary.

* One difference between `branch` and `tag`, is that `tags` can have description. Also, tag-alias never changes, but branch-alias changes with every commit made in the branch.

* If I'd like, I can refer everything in the repository using the `hash-id`, and not use branch, *HEAD* or tag.

* `git reset --hard <commit-hash-id>` will reset my HEAD of my working tree to a particular commit. `--hard` will remove/erase all the changes in my working dir. By default, it's always a `--soft` reset.

  Another way is to do a `git checkout <commit-hash-id>`.

**NOTE** - Difference between `git reset` and `git checkout` is about the state of `refs/heads/<branch>`.
`git checkout <commit-hash-id>` will move the repo in a detached *HEAD* state. Anonymous branch. Keeping the top of the master branch as-is. Firing `git checkout master` will return you the repo to old state.
`git reset <commit-hash-id>` will rollback the commits made. *HEAD* and `refs/heads/master` will be updated, and point to the same commit object. Using `--soft` (which is default) keeps the changes in your working copy, whereas `--hard` will remove all the changes. Performing another commit with same changes will give you a new commit object with a new hash-id, and there is no way to go back to previous commit object. It should be garbage collected as there is no way to back to the old commit object.

 ![Commit Architecture](images/git-1)

* Parent of any commit can be referenced by ^. Eg. `git checkout HEAD^` will replace the working tree with the parent commit of the commit object to which HEAD is pointing to currently.
  I hope by now you have figured out that *HEAD*, *<branch-name>*, *<tag-name>* are nothing but reference to particular commit-hash-id. Hence, you can also do this - `git checkout <commit-hash-id>^`. Two carets will refer to parent's parent.

* Tilde sign can take you back *n* commits. Eg. `git checkout HEAD~3` means take me 3 commits back.
* `name:path` is also very useful while doing a diff. Eg. `git diff HEAD~1:myfile.txt HEAD:myfile.txt`
*
