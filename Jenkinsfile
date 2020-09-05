@Library('jenkins-shared-library')
import com.czeffik.jenkins.shared.library.Git
import com.czeffik.jenkins.shared.library.Repository
def git = new Git(this)
def repo = new Repository(repository: 'https://github.com/Czeffik/spring.git')

echo repo.url
echo git.checkOutFrom(repo)
