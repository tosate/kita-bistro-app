<template>
  <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">Name</th>
              <th>Mahlzeit</th>
              <th>Notiz</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="meal in meals" v-bind:key="meal.id">
              <td>{{meal.child.firstName}} {{meal.child.lastName}}</td>
              <td>
                <span v-if="meal.type == 'BREAKFAST'">Frühstück</span>
            		<span v-else>Mittagessen</span>
              </td>
              <td>{{meal.notice}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
