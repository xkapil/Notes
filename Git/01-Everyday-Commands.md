### Everyday Git Commands

##### Config

`git config --global user.name "Kapil"`

`git config --global user.email "kmalhotra@xebia.com"`

##### Start with a Repo

`git init`

`git clone <url>`

`git clone username@host:url_of_repo`

##### Add Code to the Repository

`git status`

`git add <filename/dir>`

`git commit -m "<comment>"`

`git commit -a`

`git push origin master`

##### Working with remote repo

`git remote -v` // list configured remote repos

`git remote add <remote_name_like_origin> <url>` // link your repo to remote repo

`git fetch <remote_name_like_origin>`

`git merge`

`git pull`

`git merge <branch_name>`

`git rebase <branch_name>`

##### Branches

`git checkout -b <branch_name>` // create a new branch and switch

`git checkout <branch_name>` // switch

`git branch` // lists all branches

`git branch --all` // lists all remote branches as well

`git checkout -D <branch_name>` // removes the branch

`git push origin :<branch_name>` // removes branch from remote location

##### Tags

`git tag` // lists out all tags

`git tag -l "v1.1*"` // searches all tags matching the pattern

`git tag -a v1.1 -m "release 1.1"` // creates an annotated tag

`git show <tag-name>` // shows the author, message, diffs for the given tag

`git tag v1.1` // creates a light-weight tag which is just a pointer to a commit object

`git tag -a v1.1 <commit-hash-id>` // creates an annotated tag pointing to specified commit object.

`git push origin --tags` // will push all your tags. By default, `git push` doesn't pushes your tags.

##### General

`git checkout --<file_name>` // abort changes of a file

`git stash` // stashes local changes in a stack

`git stash apply` // applies last stashed changes

`git reset --hard HEAD` // by-default it's soft

`git reset <commit-hash-id>` // this will rollback any commits that I have made.

`git clean -fd` // removes untracked files and dirs
